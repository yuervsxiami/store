package com.cnuip.colligate.service.impl;

import com.cnuip.base.domain.console.Organization;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.ClientServiceUtils;
import com.cnuip.colligate.client.AuthorizeClient;
import com.cnuip.colligate.client.ConsoleClient;
import com.cnuip.colligate.client.UserClient;
import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.exception.enums.ClientEnum;
import com.cnuip.colligate.service.PatentService;
import com.cnuip.colligate.vo.PatentInfo;
import com.cnuip.colligate.vo.PatentNumVo;
import com.cnuip.colligate.vo.PatentValueVo;
import com.cnuip.colligate.vo.PatentVo;
import com.cnuip.gaea.service.web.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Crixalis on 2018/5/4.
 */

@Service
public class PatentServiceImpl implements PatentService {

    final Long APP_ID = 3l;

    private Logger logger = LoggerFactory.getLogger(PatentServiceImpl.class);

    @Autowired
    private UserClient userClient;

    @Autowired
    private AuthorizeClient authorizeClient;

    @Autowired
    private ConsoleClient organizationClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.patent.url}")
    private String syncUrl;

    @Value("${remote.tiikong.url}")
    private String tiikongUrl;

    @Value("${remote.cnuip.url}")
    private String cnuipUrl;

    @Override
    public LinkedHashMap selectPatentList(Long userId,String username, Long orgId, PatentVo patentVo) throws Exception {
        LinkedHashMap  patentMap;
        ResponseEntity<Map> responseEntity;
        Map<String,Object> resultMap = this.matchParam(userId,username,orgId,patentVo);
        String str = (String)(resultMap.get("url"));
        HashMap<String,Object> mapParam =(HashMap<String,Object>)(resultMap.get("map"));
        try{
            if("admin".equals(username)){
                responseEntity = restTemplate.exchange(syncUrl + "v2/collegePatentList/?" + str, HttpMethod.GET, null, Map.class,mapParam);
            }else{
                responseEntity = restTemplate.exchange(syncUrl + "v2/professorPatentList/?" + str, HttpMethod.GET, null, Map.class,mapParam);
            }
        }catch (Exception e){
            logger.error(ClientEnum.PATENT_ERROR+":"+e);
            throw new ClientException(ClientEnum.PATENT_ERROR,e);
        }

        // 将yyyy.MM.dd 改成yyyy-MM-dd
        patentMap = (LinkedHashMap) responseEntity.getBody().get("result");
        ArrayList list = (ArrayList) (patentMap.get("list"));
        Iterator<Object> iterator = list.iterator();
        this.matchDate(iterator);
        return patentMap;
    }

    @Override
    public Object selectPatentValueList(Long userId, String username, Long orgId, PatentVo patentVo) throws Exception{
        ResponseEntity<Map> responseEntity;
        Map<String,Object> resultMap = this.matchParam(userId,username,orgId,patentVo);
        String str = (String)(resultMap.get("url"));
        HashMap<String,Object> mapParam =(HashMap<String,Object>)(resultMap.get("map"));
        try{
            if("admin".equals(username)){
                responseEntity = restTemplate.exchange(syncUrl + "v2/collegeValueList?" + str, HttpMethod.GET, null, Map.class,mapParam);
            }else{
                responseEntity = restTemplate.exchange(syncUrl + "v2/professorValueList?" + str, HttpMethod.GET, null, Map.class,mapParam);
            }
            return responseEntity.getBody().get("result");
        }catch (Exception e){
            logger.error(ClientEnum.PATENT_ERROR+":"+e);
            throw new ClientException(ClientEnum.PATENT_ERROR,e);
        }
    }

    @Override
    public Object selectPatentDetail(String an) throws Exception {
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("an",an);
            map.put("appId",APP_ID);
            map.put("apiName","getPatentByPatentNo");
            ResponseEntity<Map> responseEntity = restTemplate.exchange(syncUrl + "v2/patentDetail?appId={appId}&apiName={apiName}&an={an}", HttpMethod.GET, null, Map.class,map);
            return responseEntity.getBody().get("result");
        }catch (Exception e){
            logger.error(ClientEnum.PATENT_ERROR+":"+e);
            throw new ClientException(ClientEnum.PATENT_ERROR,e);
        }
    }

    @Override
    public Object selectPatentReport(String an) throws Exception {
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("an",an);
            ResponseEntity<Map> responseEntity = restTemplate.exchange(syncUrl + "v2/patentReport?an={an}", HttpMethod.GET, null, Map.class,map);
            return responseEntity.getBody().get("result");
        }catch (Exception e){
            logger.error(ClientEnum.PATENT_ERROR+":"+e);
            throw new ClientException(ClientEnum.PATENT_ERROR,e);
        }
    }

    @Override
    public ArrayList selectSelfPatent(Long userId,String anOrTi) throws Exception{

        // 发明人
        User user = this.searchUserInfo(userId);
        String pin = user.getRealName();
        if(pin == null){
            throw new ClientException(ClientEnum.USER_NO_EXIST);
        }

        // 权利人
        Organization organization = this.searchOrganizationInfo(user.getOrganizationId());
        String ph = organization.getName();
        if(ph == null){
            throw new ClientException(ClientEnum.ORGANIZATION_NO_EXIST);
        }

        StringBuffer ans = new StringBuffer();

        // 获取已被使用的专利号
        Map<String,Object> param = new HashMap<>();
        param.put("realname",pin);
        param.put("orgName",ph);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl + "cnuip2-mservice-shop/v1/patent/commercialization?realname={realname}&orgName={orgName}", HttpMethod.GET, null, Map.class,param);
        logger.info("CNUIP2 SHOP CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
        List<String> anList = (List) responseEntity.getBody().get("result");

        for(String an:anList){
            ans.append(an+";");
        }

        // 查询有效的专利列表
        ArrayList patentList = this.searchPatentList(pin,ph,anOrTi,ans.toString());
        Iterator<Object> iterator = patentList.iterator();
        this.matchDate(iterator);

        return patentList;
    }

    @Override
    public Object selectStatistics(Long userId, Long orgId) throws Exception{
        // 查询用户信息
        User user = this.searchUserInfo(userId);

        try{
            Map<String,Object> map = new HashMap<>();
            if(!"admin".equals(user.getUsername())) {
                map.put("collegeName", user.getOrganizationName());
                map.put("name", user.getRealName());
                return restTemplate.exchange(syncUrl + "v2/professorCount?collegeName={collegeName}&name={name}", HttpMethod.GET, null, Map.class, map).getBody().get("result");
            }else{
                map.put("collegeName", user.getOrganizationName());
                return restTemplate.exchange(syncUrl + "v2/collegeCount?collegeName={collegeName}", HttpMethod.GET, null, Map.class, map).getBody().get("result");
            }
        }catch (Exception e){
            logger.error(ClientEnum.PATENT_ERROR+":"+e);
            throw new ClientException(ClientEnum.PATENT_ERROR);
        }
    }

    @Override
    public PatentNumVo selectUseStatistics(Long userId) throws Exception{

        // 发明人
        User user = this.searchUserInfo(userId);

        String pin = user.getRealName();
        if(pin == null){
            throw new ClientException(ClientEnum.USER_NO_EXIST);
        }

        // 权利人
        Organization organization = this.searchOrganizationInfo(user.getOrganizationId());
        String ph = organization.getName();
        if(ph == null){
            throw new ClientException(ClientEnum.ORGANIZATION_NO_EXIST);
        }

        // 查询有效的专利
        ArrayList list = this.searchPatentList(pin,ph,null,null);
        Long validNum = (long)list.size();

        // 获取已被使用的专利号
        Map<String,Object> param = new HashMap<>();
        param.put("realname",pin);
        param.put("orgName",ph);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl + "cnuip2-mservice-shop/v1/patent/commercialization?realname={realname}&orgName={orgName}", HttpMethod.GET, null, Map.class,param);
        logger.info("CNUIP2 SHOP CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
        List<String> anList = (List) responseEntity.getBody().get("result");

        Long usedNum = (long)anList.size();
        PatentNumVo patentNumVo = new PatentNumVo();
        patentNumVo.setValidNum(validNum);
        patentNumVo.setUsedNum(usedNum);
        patentNumVo.setUsableNum(validNum - usedNum);
        return patentNumVo;
    }

    @Override
    public Object selectQuoteList(String an) throws Exception{
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("an",an);
            map.put("appId",APP_ID);
            ResponseEntity<Map> responseEntity = restTemplate.exchange(syncUrl + "v2/patentQuoteList?an={an}&appId={appId}", HttpMethod.GET, null, Map.class,map);
            return responseEntity.getBody().get("result");
        }catch (Exception e){
            logger.error(ClientEnum.PATENT_ERROR+":"+e);
            throw new ClientException(ClientEnum.PATENT_ERROR,e);
        }
    }

    @Override
    public Object selectSimilarityInfo(String an, Long pageNum, Long pageSize) throws Exception{
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("an",an);
            map.put("appId",APP_ID);
            map.put("pageNum",pageNum);
            map.put("pageSize",pageSize);
            ResponseEntity<Map> responseEntity = restTemplate.exchange(syncUrl + "v2/patentSimilarityInfo?an={an}&appId={appId}&pageNum={pageNum}&pageSize={pageSize}", HttpMethod.GET, null, Map.class,map);
            return responseEntity.getBody().get("result");
        }catch (Exception e){
            logger.error(ClientEnum.PATENT_ERROR+":"+e);
            throw new ClientException(ClientEnum.PATENT_ERROR,e);
        }
    }

    @Override
    public PatentInfo selectKnowledgeReport(Long userId, String username, Long orgId) throws Exception{

        if("admin".equals(username)){
            return null;
        }

        PatentInfo patentInfo = new PatentInfo();
        try {
            // 查询用户信息
            User user = this.searchUserInfo(userId);
            if("admin".equals(user.getUsername())){
                return null;
            }
            String orgName = user.getOrganizationName();
            String realName = user.getRealName();

            Map<String, Object> params = new HashMap<>();
            params.put("expert",realName);
            params.put("assignee",orgName);

            // 查询专利信息
            ResponseEntity<Map> patentEntity = restTemplate.exchange(syncUrl + "v2/professorCount?name={expert}&collegeName={assignee}", HttpMethod.GET, null, Map.class,params);
            LinkedHashMap patentResult = (LinkedHashMap)patentEntity.getBody().get("result");
            if(patentResult == null){
                return null;
            }
            patentInfo.setPh(orgName);
            patentInfo.setPinSplit(String.valueOf(patentResult.get("pinSplit")));
            patentInfo.setFmzlCount(Long.valueOf(String.valueOf(patentResult.get("fmzlCount"))));
            patentInfo.setSyxxCount(Long.valueOf(String.valueOf((patentResult.get("syxxCount")))));
            patentInfo.setWgzlCount(Long.valueOf(String.valueOf((patentResult.get("wgzlCount")))));
            patentInfo.setYxCount(Long.valueOf(String.valueOf((patentResult.get("yxCount")))));
            patentInfo.setWxCount(Long.valueOf(String.valueOf((patentResult.get("wxCount")))));
            patentInfo.setCount(Long.valueOf(String.valueOf((patentResult.get("count")))));

            // 查询专家报告信息
            ResponseEntity<Map> pinEntity = restTemplate.exchange(syncUrl + "v2/pinReport?name={expert}&collegeName={assignee}", HttpMethod.GET, null, Map.class,params);
            LinkedHashMap pinResult = (LinkedHashMap)pinEntity.getBody().get("result");
            if(pinResult == null){
                return null;
            }
            patentInfo.setAn(String.valueOf(pinResult.get("an")));
            patentInfo.setTi(String.valueOf(pinResult.get("ti")));
            patentInfo.setPatentValue(String.format("%.2f", Double.valueOf(pinResult.get("patentValue").toString())*100));
            patentInfo.setSurpass(String.format("%.2f", Double.valueOf(pinResult.get("surpass").toString())*100)+"%");
            patentInfo.setYxPatentValueAvg(String.format("%.2f", Double.valueOf(pinResult.get("yxPatentValueAvg").toString())*100));
            patentInfo.setYxPatentPrice(String.format("%.2f", Double.valueOf(pinResult.get("yxPatentPrice").toString())));
            patentInfo.setWxPatentPrice(String.format("%.2f", Double.valueOf(pinResult.get("wxPatentPrice").toString())));

            logger.warn("PMES CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");

            return patentInfo;
        } catch (RestClientException e) {
            logger.warn("PMES CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
            throw new ClientException(ClientEnum.PMES_CONNECTION_ERROR,e);
        }
    }

    @Override
    public List<PatentValueVo> priceReport(Long userId, String username, Long orgId) throws  Exception{
        if("admin".equals(username)){
            return null;
        }
        List<PatentValueVo> patentValueVos=new ArrayList<>();
        try{
            User user = this.searchUserInfo(userId);
            if("admin".equals(user.getUsername())){
                return null;
            }
            String orgName = user.getOrganizationName();
            String realName = user.getRealName();

            Map<String, Object> params = new HashMap<>();
            params.put("expert",realName);
            params.put("assignee",orgName);
            ResponseEntity<Map> pinEntity = restTemplate.exchange(syncUrl + "v2/pinReport?name={expert}&collegeName={assignee}", HttpMethod.GET, null, Map.class,params);
            LinkedHashMap pinResult = (LinkedHashMap)pinEntity.getBody().get("result");
            if(pinResult == null){
                return null;
            }
            PatentValueVo patentValueVoXY=new PatentValueVo();
            patentValueVoXY.setType("有效专利总价格");
            patentValueVoXY.setPrice(String.format("%.2f", Double.valueOf(pinResult.get("yxPatentPrice").toString())*100));
            patentValueVoXY.setCount(Long.valueOf(String.valueOf((pinResult.get("yxCount")))));
            patentValueVos.add(patentValueVoXY);
            PatentValueVo patentValueVoWY=new PatentValueVo();
            patentValueVoWY.setType("无效专利总价格");
            patentValueVoWY.setPrice(String.format("%.2f", Double.valueOf(pinResult.get("wxPatentPrice").toString())*100));
            patentValueVoWY.setCount(Long.valueOf(String.valueOf((pinResult.get("wxCount")))));
            patentValueVos.add(patentValueVoWY);
        }catch (Exception e){
            throw new ClientException(ClientEnum.PMES_CONNECTION_ERROR,e);
        }
        return patentValueVos;
    }

    @Override
    public Map selectwebStatistics(Long userId, Long orgId) throws  Exception{
        // 查询用户信息
        User user = this.searchUserInfo(userId);
        try{
            Map<String,Object> map = new HashMap<>();
            Map<String,Object> resultMap = new HashMap<>();
            if(!"admin".equals(user.getUsername())) {
                map.put("collegeName", user.getOrganizationName());
                map.put("name", user.getRealName());
                Map result = (Map) restTemplate.exchange(syncUrl + "v2/professorCount?collegeName={collegeName}&name={name}", HttpMethod.GET, null, Map.class, map).getBody().get("result");
                resultMap.put("q", mapToList(result));
            }else{
                map.put("collegeName", user.getOrganizationName());
                Map result = (Map)restTemplate.exchange(syncUrl + "v2/collegeCount?collegeName={collegeName}", HttpMethod.GET, null, Map.class, map).getBody().get("result");
                Map result2 = (Map)restTemplate.exchange(syncUrl + "v2/collegeInfo?collegeName={collegeName}", HttpMethod.GET, null, Map.class, map).getBody().get("result");
                resultMap.put("q", mapToList(result));
                resultMap.put("s", mapToList(result2));
            }
            return resultMap;
        }catch (Exception e){
            logger.error(ClientEnum.PATENT_ERROR+":"+e);
            throw new ClientException(ClientEnum.PATENT_ERROR);
        }
    }

    @Override
    public Map<String, Object> collegeContributeRank(Long userId, String username, Long orgId) throws ClientException {
        try {
            User user = this.searchUserInfo(userId);
            String orgName = user.getOrganizationName();
            Map<String, Object> params = new HashMap<>();
            params.put("assignee",orgName);
            return (Map<String, Object>) restTemplate.exchange(syncUrl + "v2/collegeContributeRank?collegeName={assignee}", HttpMethod.GET, null, Map.class, params).getBody().get("result");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientException(ClientEnum.SERVICE_ERROR,e);
        }
    }

    @Override
    public List<Map<String, Object>> collegeTransCount(Long userId, String username, Long orgId) throws ClientException {
        try {
            User user = this.searchUserInfo(userId);
            String orgName = user.getOrganizationName();
            Map<String, Object> params = new HashMap<>();
            params.put("assignee",orgName);
            return (List<Map<String, Object>>) restTemplate.exchange(syncUrl + "v2/collegeTransCount?collegeName={assignee}", HttpMethod.GET, null, Map.class, params).getBody().get("result");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientException(ClientEnum.SERVICE_ERROR,e);
        }
    }

    @Override
    public List<Map<String, Object>> getCollegeApplyYear(Long userId, String username, Long orgId) throws ClientException {
        try {
            User user = this.searchUserInfo(userId);
            String orgName = user.getOrganizationName();
            Map<String, Object> params = new HashMap<>();
            params.put("assignee",orgName);
            return (List<Map<String, Object>>) restTemplate.exchange(syncUrl + "v2/collegeApplyYear?collegeName={assignee}", HttpMethod.GET, null, Map.class, params).getBody().get("result");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientException(ClientEnum.SERVICE_ERROR,e);
        }
    }

    @Override
    public Map<String, Object> getCollegePaCount(Long userId, String username, Long orgId) throws ClientException {
        try {
            User user = this.searchUserInfo(userId);
            String orgName = user.getOrganizationName();
            Map<String, Object> params = new HashMap<>();
            params.put("assignee",orgName);
            return (Map<String, Object>) restTemplate.exchange(syncUrl + "v2/getCollegePaCount?collegeName={assignee}", HttpMethod.GET, null, Map.class, params).getBody().get("result");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientException(ClientEnum.SERVICE_ERROR,e);
        }
    }

    @Override
    public List<Map<String, Object>> professorApplyYear(Long userId, String username, Long orgId) throws ClientException {
        try {
            User user = this.searchUserInfo(userId);
            String orgName = user.getOrganizationName();
            Map<String, Object> params = new HashMap<>();
            params.put("assignee",orgName);
            params.put("name", user.getRealName());
            return (List<Map<String, Object>>) restTemplate.exchange(syncUrl + "v2/professorApplyYear?collegeName={assignee}&name={name}", HttpMethod.GET, null, Map.class, params).getBody().get("result");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientException(ClientEnum.SERVICE_ERROR,e);
        }
    }

    @Override
    public Map<String, Object> professorValueRank(Long userId, String username, Long orgId) throws ClientException {
        try {
            User user = this.searchUserInfo(userId);
            String orgName = user.getOrganizationName();
            Map<String, Object> params = new HashMap<>();
            params.put("assignee",orgName);
            params.put("name", user.getRealName());
            return (Map<String, Object>) restTemplate.exchange(syncUrl + "v2/professorValueRank?collegeName={assignee}&name={name}", HttpMethod.GET, null, Map.class, params).getBody().get("result");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientException(ClientEnum.SERVICE_ERROR,e);
        }
    }

    @Override
    public List<Map<String, Object>> professorTransCount(Long userId, String username, Long orgId) throws ClientException {
        try {
            User user = this.searchUserInfo(userId);
            String orgName = user.getOrganizationName();
            Map<String, Object> params = new HashMap<>();
            params.put("assignee",orgName);
            params.put("name", user.getRealName());
            return (List<Map<String, Object>>) restTemplate.exchange(syncUrl + "v2/professorTransCount?collegeName={assignee}&name={name}", HttpMethod.GET, null, Map.class, params).getBody().get("result");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientException(ClientEnum.SERVICE_ERROR,e);
        }
    }

    // 根据用户ID查询用户信息
    private User searchUserInfo(Long userId) throws Exception{

        ApiResponse<User> userRes = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryDetail", userId);
        logger.info("USER CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
        if(userRes.getCode() == 0){
            return userRes.getResult();
        }else{
            logger.warn("USER CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
            throw new ClientException(ClientEnum.USER_ERROR);
        }
    }

    // 根据组织ID查询用户信息
    private Organization searchOrganizationInfo(Long orgId) throws Exception{

        ApiResponse<Organization> orgRes = new ClientServiceUtils<Organization, ConsoleClient>().exec(organizationClient, "queryDetail", orgId);
        logger.info("ORGANIZATION CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
        if(orgRes.getCode() == 0){
            return orgRes.getResult();
        }else{
            logger.warn("CONSOLE CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
            throw new ClientException(ClientEnum.ORGANIZATION_ERROR);
        }
    }

    // 查询有效的专利列表
    private ArrayList searchPatentList(String pin,String ph, String anOrTi,String ans) throws Exception{

        // 查询有效的专利列表
        LinkedHashMap  patentMap;
        ArrayList patentList;
        try{
            StringBuilder builder = new StringBuilder();
            builder.append(syncUrl+"v2/professorPatentList?pin={pin}&ph={ph}&lastLegalStatus={lastLegalStatus}&pageSize={pageSize}&pageNum={pageNum}&ans={ans}");
            Map<String,Object> map = new HashMap<>();
            map.put("pin",pin);
            map.put("ph",ph);
            map.put("lastLegalStatus","有效,在审");
            map.put("pageSize",0);
            map.put("pageNum",0);
            map.put("ans",ans);
            if(anOrTi != null){
                map.put("anOrTi", anOrTi);
                builder.append("&anOrTi={anOrTi}");
            }
            ResponseEntity<Map> responseEntity = restTemplate.exchange(builder.toString(), HttpMethod.GET, null, Map.class,map);
            patentMap = (LinkedHashMap) responseEntity.getBody().get("result");
            patentList = (ArrayList)(patentMap.get("list"));
            logger.info("PMES CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
            return patentList;
        }catch (Exception e){
            logger.error(ClientEnum.PATENT_ERROR+":"+e);
            throw new ClientException(ClientEnum.PATENT_ERROR);
        }
    }

    // 拼接参数
    private Map<String,Object> matchParam(Long userId,String username, Long orgId, PatentVo patentVo) throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        // 权利人
        Organization organization = this.searchOrganizationInfo(orgId);
        map.put("ph",organization.getName());
        stringBuilder.append("ph={ph}&");

        // 申请号或专利名称
        if (patentVo.getAnOrTi() != null) {
            map.put("anOrTi",patentVo.getAnOrTi());
            stringBuilder.append("anOrTi={anOrTi}&");
        }

        if (patentVo.getPins() != null) {
            map.put("pins",patentVo.getPins());
            stringBuilder.append("pins={pins}&");
        }

        // 发明人
        if("admin".equals(username)){
            if(patentVo.getPin() != null){
                map.put("pin",patentVo.getPin());
                stringBuilder.append("pin={pin}&");
            }
        }else{
            User user = this.searchUserInfo(userId);
            map.put("pin",user.getRealName());
            stringBuilder.append("pin={pin}&");
        }
        // 专利类型
        if(patentVo.getSectionName() != null){
            map.put("sectionName",patentVo.getSectionName());
            stringBuilder.append("sectionName={sectionName}&");
        }
        // 专利类型
        if(patentVo.getType() != null){
            map.put("type",patentVo.getType());
            stringBuilder.append("type={type}&");
        }
        // 专利状态
        if(patentVo.getLastLegalStatus() != null){
            map.put("lastLegalStatus",patentVo.getLastLegalStatus());
            stringBuilder.append("lastLegalStatus={lastLegalStatus}&");
        }
        // app用户根据专利价值范围查询
        if(!("admin".equals(username))){
            // 专利价值最小值
            if(patentVo.getMinPatentValue() != null){
                map.put("minPatentValue",patentVo.getMinPatentValue());
                stringBuilder.append("minPatentValue={minPatentValue}&");
            }
            // 专利价值最大值
            if(patentVo.getMaxPatentValue() != null){
                map.put("maxPatentValue",patentVo.getMaxPatentValue());
                stringBuilder.append("maxPatentValue={maxPatentValue}&");
            }
        }
        // 开始时间
        if(patentVo.getStartTime() != null){
            map.put("startTime",patentVo.getStartTime());
            stringBuilder.append("startTime={startTime}&");
        }
        // 结束时间
        if(patentVo.getEndTime() != null){
            map.put("endTime",patentVo.getEndTime());
            stringBuilder.append("endTime={endTime}&");
        }
        if (patentVo.getPageNum() == null) {
            map.put("pageNum",1);
        } else {
            map.put("pageNum",patentVo.getPageNum());
        }
        if (patentVo.getPageSize() == null) {
            map.put("pageSize",10);
        } else {
            map.put("pageSize",patentVo.getPageSize());
        }
        stringBuilder.append("pageNum={pageNum}&");
        stringBuilder.append("pageSize={pageSize}");
        resultMap.put("map",map);
        resultMap.put("url",stringBuilder.toString());
        return resultMap;
    }

    private void matchDate(Iterator<Object> iterator){
        while(iterator.hasNext()){
            Object patent = iterator.next();
            if(patent instanceof LinkedHashMap){
                LinkedHashMap map = (LinkedHashMap)patent;
                // 将yyyy.MM.dd 改成yyyy-MM-dd
                String ad = (String) ((LinkedHashMap) patent).get("ad");
                map.put("ad",ad.replaceAll("\\.","-"));
            }
        }
    }

    public  static List mapToList(Map m){
        return (ArrayList)m.keySet().stream().map(s -> {
            List a = new ArrayList();
            Map b = new HashMap();
            b.put("name", s);
            b.put("value", m.get(s));
            a.add(b);
            return a.get(0);
        }).collect(Collectors.toList());
    }
}

package com.cnuip.colligate.service.impl;

import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.ClientServiceUtils;
import com.cnuip.colligate.client.UserClient;
import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.exception.enums.ClientEnum;
import com.cnuip.colligate.service.TiiKongService;
import com.cnuip.colligate.vo.PatentInfo;
import com.cnuip.gaea.service.web.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TiiKongServiceImpl implements TiiKongService {

    private Logger logger = LoggerFactory.getLogger(TiiKongServiceImpl.class);

    @Autowired
    private UserClient userClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.tiikong.url}")
    private String tiikongUrl;

    @Override
    public Object invoke(Map<String, Object> params) throws Exception {
        Object result;
        try {
            String name = String.valueOf(params.get("name"));
            if(name == null){
                throw new ClientException(ClientEnum.NAME_NULL_ERROR);
            }
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("API_NAME", name);
            httpHeaders.add("APP_ID", "3");
            HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
            params.remove("name");
            // 去除map中的空值
            Map<String,Object> param = this.handleParam(params);
            // 拼接url
            String str = this.handleUrl(param);
            ResponseEntity<Map> responseEntity = restTemplate.exchange(tiikongUrl+"tiikong"+str, HttpMethod.GET, requestEntity, Map.class,param);
            if(!(boolean)(responseEntity.getBody().get("success"))){
                logger.error("TIIKONG CONNECT ERROR",responseEntity.getBody().get("error"));
                throw new ClientException(ClientEnum.TIIKONG_CONNECTION_ERROR);
            }
            result = responseEntity.getBody().get("result");
            logger.warn("TIIKONG CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
            return result;
        } catch (RestClientException e) {
            logger.warn("TIIKONG CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
            throw new ClientException(ClientEnum.TIIKONG_CONNECTION_ERROR,e);
        }
    }

    @Override
    public String getUserToken(Long userId) throws Exception{

        String result;
        try {
            // 查询用户信息
            ApiResponse<User> userRes = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryDetail", userId);
            if (userRes.getCode() != 0) {
                logger.warn("USER CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
                throw new ClientException(ClientEnum.USER_ERROR);
            }
            Map<String, Object> params = new HashMap<>();
            params.put("user_name",userRes.getResult().getRealName());
            params.put("tenant_name",userRes.getResult().getOrganizationName());
            params.put("user_id",userId);
            // 去除map中的空值
            Map<String,Object> param = this.handleParam(params);
            // 拼接url
            String str = this.handleUrl(param);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("API_NAME", "getUserToken");
            httpHeaders.add("APP_ID", "3");
            HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
            ResponseEntity<Map> responseEntity = restTemplate.exchange(tiikongUrl+"tiikong"+str, HttpMethod.GET, requestEntity, Map.class,param);
            result = (String) responseEntity.getBody().get("result");
            logger.warn("GETUSERTOKEN CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
            return result;
        } catch (RestClientException e) {
            logger.warn("TIIKONG CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
            throw new ClientException(ClientEnum.TIIKONG_CONNECTION_ERROR,e);
        }
    }

    @Override
    public List getCompanyRecommendData(Long userId, String username) throws ClientException {
        // 查询用户信息
        ApiResponse<User> userRes = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryDetail", userId);
        if (userRes.getCode() != 0) {
            logger.warn("USER CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
            throw new ClientException(ClientEnum.USER_ERROR);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("assignee",userRes.getResult().getOrganizationName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("API_NAME", "getCompanyRecommendData");
        httpHeaders.add("APP_ID", "3");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
        //查询专家标引信息
        ResponseEntity<Map> responseEntity = restTemplate.exchange(tiikongUrl+"tiikong?companyName={assignee}", HttpMethod.GET, requestEntity, Map.class,params);
        if(!(boolean)(responseEntity.getBody().get("success"))){
            logger.error("TIIKONG CONNECT ERROR",responseEntity.getBody().get("error"));
            throw new ClientException(ClientEnum.TIIKONG_CONNECTION_ERROR);
        }
        logger.warn("TIIKONG CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
        LinkedHashMap result = (LinkedHashMap)responseEntity.getBody().get("result");
        return ((List) result.get("assigneesList")).subList(0, 5);
    }

    @Override
    public PatentInfo getScientificInfo(Long userId, String username) throws Exception{
        if("admin".equals(username)){
            return null;
        }
        try {
            // 查询用户信息
            ApiResponse<User> userRes = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryDetail", userId);
            if (userRes.getCode() != 0) {
                logger.warn("USER CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
                throw new ClientException(ClientEnum.USER_ERROR);
            }
            if ("admin".equals(userRes.getResult().getUsername())) {
                return null;
            }

            Map<String, Object> params = new HashMap<>();
            params.put("expert",userRes.getResult().getRealName());
            params.put("assignee",userRes.getResult().getOrganizationName());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("API_NAME", "getExpertRecommendation");
            httpHeaders.add("APP_ID", "3");
            HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
            //查询专家标引信息
            ResponseEntity<Map> responseEntity = restTemplate.exchange(tiikongUrl+"tiikong?expert={expert}&assignee={assignee}", HttpMethod.GET, requestEntity, Map.class,params);
            if(!(boolean)(responseEntity.getBody().get("success"))){
                logger.error("TIIKONG CONNECT ERROR",responseEntity.getBody().get("error"));
                throw new ClientException(ClientEnum.TIIKONG_CONNECTION_ERROR);
            }
            logger.warn("TIIKONG CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
            LinkedHashMap result = (LinkedHashMap)responseEntity.getBody().get("result");
            LinkedHashMap keywordsTop = (LinkedHashMap)result.get("keywords");
            LinkedHashMap expertsTop = (LinkedHashMap)result.get("experts");
            LinkedHashMap companiesTop = (LinkedHashMap)result.get("companies");

            PatentInfo patentInfo = new PatentInfo();

            List scientificList = new ArrayList();
            if(keywordsTop != null){
                List keywords = ((ArrayList)keywordsTop.get("keywords")).subList(0,3);
                List scores = ((ArrayList)keywordsTop.get("scores")).subList(0,3);
                patentInfo.setKeywords(keywords);
                patentInfo.setScores(scores);
                for(int i=0;i<3;i++){
                    Map<String,Object> map = new HashMap<>();
                    map.put("name",keywords.get(i));
                    map.put("value",scores.get(i));
                    scientificList.add(map);
                }
                patentInfo.setScientificList(scientificList);
            }

            if(expertsTop != null){
                patentInfo.setExpertsNum(String.valueOf(expertsTop.get("experts_num")));
            }
            if(companiesTop != null){
                patentInfo.setCompaniesNum(String.valueOf(companiesTop.get("companies_num")));
            }
            return patentInfo;
        } catch (RestClientException e) {
            logger.warn("TIIKONG CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
            throw new ClientException(ClientEnum.TIIKONG_CONNECTION_ERROR,e);
        }
    }

    @Override
    public PatentInfo getSimiliarExperts(Long userId, String username) throws Exception {
        if("admin".equals(username)){
            return null;
        }
        try {
            // 查询用户信息
            ApiResponse<User> user = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryDetail", userId);
            if (user.getCode() != 0) {
                logger.warn("USER CONNECT ERROR");
                throw new ClientException(ClientEnum.USER_ERROR);
            }
            if ("admin".equals(user.getResult().getUsername())) {
                return null;
            }
            Map<String, Object> params = new HashMap<>();
            params.put("expert",user.getResult().getRealName());
            params.put("assignee",user.getResult().getOrganizationName());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("API_NAME", "getExpertRecommendation");
            httpHeaders.add("APP_ID", "3");
            HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
            //查询专家标引信息
            ResponseEntity<Map> responseEntity = restTemplate.exchange(tiikongUrl+"tiikong?expert={expert}&assignee={assignee}", HttpMethod.GET, requestEntity, Map.class,params);
            if(!(boolean)(responseEntity.getBody().get("success"))){
                logger.error("TIIKONG CONNECT ERROR",responseEntity.getBody().get("error"));
                throw new ClientException(ClientEnum.TIIKONG_CONNECTION_ERROR);
            }
            logger.warn("TIIKONG CONNECT FINISHED");
            LinkedHashMap result = (LinkedHashMap)responseEntity.getBody().get("result");
            LinkedHashMap experts = (LinkedHashMap)result.get("experts");
            LinkedHashMap companies = (LinkedHashMap)result.get("companies");

            PatentInfo patentInfo = new PatentInfo();

            List similiarExpertsList = new ArrayList();
            if(experts != null){
                List expertsList = ((ArrayList)experts.get("experts_namelist")).subList(0,5);
                List scores = ((ArrayList)experts.get("experts_scorelist")).subList(0,5);
                for(int i=0;i<5;i++){
                    Map<String,Object> map = new HashMap<>();
                    map.put("expert",expertsList.get(i));
                    map.put("percentage",scores.get(i));
                    similiarExpertsList.add(map);
                }
                patentInfo.setSimiliarExpertsList(similiarExpertsList);
            }
            List similiarCampanyList = new ArrayList();
            if(companies != null){
                List companiesList = ((ArrayList)companies.get("companies_namelist")).subList(0,5);
                List scores = ((ArrayList)companies.get("companies_scorelist")).subList(0,5);
                for(int j=0;j<5;j++){
                    Map<String,Object> map = new HashMap<>();
                    map.put("company",companiesList.get(j));
                    map.put("percentage",scores.get(j));
                    similiarCampanyList.add(map);
                }
                patentInfo.setCooperationCampanys(similiarCampanyList);
            }
            return patentInfo;
        } catch (RestClientException e) {
            logger.warn("TIIKONG CONNECT ERROR+++++++++++++++++++++++++++++++++++++++++++");
            throw new ClientException(ClientEnum.TIIKONG_CONNECTION_ERROR,e);
        }
    }

    @Override
    public List companyDevelopDirection(Long userId, String username) throws Exception{
        ApiResponse<User> userRes = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryDetail", userId);
        if (userRes.getCode() != 0) {
            logger.warn("USER CONNECT ERROR");
            throw new ClientException(ClientEnum.USER_ERROR);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("assignee",userRes.getResult().getOrganizationName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("API_NAME", "getCompanyRecommendData");
        httpHeaders.add("APP_ID", "3");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
        //查询专家标引信息
        ResponseEntity<Map> responseEntity = restTemplate.exchange(tiikongUrl+"tiikong?companyName={assignee}", HttpMethod.GET, requestEntity, Map.class,params);
        if(!(boolean)(responseEntity.getBody().get("success"))){
            logger.error("TIIKONG CONNECT ERROR",responseEntity.getBody().get("error"));
            throw new ClientException(ClientEnum.TIIKONG_CONNECTION_ERROR);
        }
        logger.warn("TIIKONG CONNECT FINISHED+++++++++++++++++++++++++++++++++++++++++++");
        LinkedHashMap result = (LinkedHashMap)responseEntity.getBody().get("result");
        return ((List) result.get("recommendList")).subList(0, 3);
    }

    private Map<String, Object> handleParam(Map<String, Object> params){
        Map<String, Object> param = new HashMap<>();
        params.forEach((key, value) -> {
            if(value != null){
                param.put(key,value);
            }
        });
        return param;
    }

    private String handleUrl(Map<String, Object> params){
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        params.forEach((key, value) -> {
            sb.append("&");
            sb.append(key);
            sb.append("={");
            sb.append(key);
            sb.append("}");
        });
        return sb.toString();
    }


}

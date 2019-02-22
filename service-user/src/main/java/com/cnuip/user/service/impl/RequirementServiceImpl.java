package com.cnuip.user.service.impl;

import com.cnuip.base.domain.params.RequirementParam;
import com.cnuip.base.domain.user.Requirement;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.user.repository.RequirementMapper;
import com.cnuip.user.repository.base.RequirementBaseMapper;
import com.cnuip.user.rest.exceptions.CustomException;
import com.cnuip.user.rest.exceptions.ResponseEnum;
import com.cnuip.user.service.RequirementService;
import com.cnuip.user.vo.RequirementCountVo;
import com.cnuip.user.vo.param.RequirementQueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

/**
 * @Author: 王志斌
 * @Date: 2018/4/3 15:27
 */
@Service
@Transactional(readOnly = true)
public class RequirementServiceImpl extends AbstractServiceImpl<Requirement, RequirementParam> implements RequirementService {

    private Logger logger = LoggerFactory.getLogger(RequirementServiceImpl.class);

    @Autowired
    private RequirementBaseMapper requirementBaseMapper;

    @Autowired
    private RequirementMapper requirementMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.cnuip.url}")
    private String cnuipUrl;

    @Override
    public LinkedHashMap findRequirementList(Long userId, String username, RequirementQueryParam param) throws Exception{
        // 查询需求ids
        RequirementParam requirementParam = new RequirementParam();
        requirementParam.setUserId(userId);
        List<Requirement> requirementList = requirementBaseMapper.getAll(requirementParam);
        if(requirementList == null || requirementList.size() == 0){
            return null;
        }
        //拼接字符串
        StringBuilder stringBuilder = new StringBuilder();
        for(Requirement requirement:requirementList){
            stringBuilder.append(requirement.getRequirementId()+",");
        }
        stringBuilder.setLength(stringBuilder.length()-1);
        //admin用户查询所有需求
        if("admin".equals(username)){
            stringBuilder.setLength(0);
        }

        LinkedHashMap  requirementListMap;
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("ids", stringBuilder.toString());
            map.put("title", param.getTitle());
            map.put("code", param.getCode());
            map.put("userId", userId);
            map.put("startTime", param.getStartTime() == null ? null:param.getStartTime().getTime());
            map.put("endTime", new Date().getTime());
            map.put("isReply", param.getIsReply());
            map.put("enterpriseType", param.getEnterpriseType());
            map.put("pageNum", param.getPageNum());
            map.put("pageSize", param.getPageSize());

            String str = cnuipUrl+"cnuip2-mservice-user/v1/requirement/list?ids={ids}&title={title}&code={code}&userId={userId}&startTime={startTime}&endTime={endTime}&isReply={isReply}&enterpriseType={enterpriseType}&pageNum={pageNum}&pageSize={pageSize}";

            ResponseEntity<Map> responseEntity = restTemplate.exchange(str, HttpMethod.GET, null, Map.class,map);
            requirementListMap = (LinkedHashMap) responseEntity.getBody().get("result");
            logger.info("SEARCH REQIREMENT LIST CONNECT END++++++++++++++++++++++++++++");
            if(!responseEntity.getBody().get("code").toString().equals("0")){
                if(responseEntity.getBody().get("code").toString().equals("806")){
                    throw new CustomException(ResponseEnum.SHOP_NULL_ERROR);
                }
                logger.error("SEARCH REQIREMENT LIST ERRER++++++++++++++++++++++++++++");
                throw new CustomException(ResponseEnum.CNUIP_CONNECT_ERROR);
            }
        }catch (Exception e){
            if (e instanceof CustomException) {
                throw e;
            }
            logger.error("SEARCH REQIREMENT LIST ERRER++++++++++++++++++++++++++++");
            throw new CustomException(e,ResponseEnum.CNUIP_CONNECT_ERROR);
        }
        return requirementListMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LinkedHashMap reply(Long userId, Long requirementId, String replyContent) throws Exception{
        RequirementParam requirementParam = new RequirementParam();
        requirementParam.setRequirementId(requirementId);
        requirementParam.setUserId(userId);
        Requirement requirement = requirementBaseMapper.selectOne(requirementParam);
        if (null == requirement) {
            throw new CustomException(ResponseEnum.REQUIREMENT_ERROR);
        }
        requirement.setReplyContent(replyContent);
        requirementBaseMapper.update(requirementParam, requirement);

        LinkedHashMap  requirementListMap;
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("requirementId", requirementId);
            map.put("content", replyContent);
            map.put("remoteKey", userId);

            String str = cnuipUrl+"cnuip2-mservice-user/v1/requirement/comment?requirementId={requirementId}&content={content}&remoteKey={remoteKey}";

            ResponseEntity<Map> responseEntity = restTemplate.exchange(str, HttpMethod.GET, null, Map.class,map);
            requirementListMap = (LinkedHashMap) responseEntity.getBody().get("result");
            logger.info("REPLE REQIREMENT CONNECT END++++++++++++++++++++++++++++");
            if(!responseEntity.getBody().get("code").toString().equals("0")) {
                requirementListMap = (LinkedHashMap) responseEntity.getBody();
            }
        }catch (Exception e){
            logger.error("REPLE REQIREMENT ERRER++++++++++++++++++++++++++++");
            throw new CustomException(ResponseEnum.CNUIP_CONNECT_ERROR);
        }
        return requirementListMap;
    }

    @Override
    public LinkedHashMap findRequirementDetail(Long userId,Long requirementId) throws Exception{
        if(requirementId == null || requirementId == 0){
            throw new CustomException(ResponseEnum.REQUIREMENT_ERROR);
        }
        LinkedHashMap pushDetailMap;
        try{
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(cnuipUrl + "cnuip2-mservice-user/v1/requirement/comment/detail")
                    .queryParam("requirementId", requirementId)
                    .queryParam("remoteKey", String.valueOf(userId));
            Map<String,Object> map = new HashMap<>();
            map.put("requirementId", requirementId);
            map.put("remoteKey", userId);

            String str = cnuipUrl + "cnuip2-mservice-user/v1/requirement/comment/detail?requirementId={requirementId}&remoteKey={remoteKey}";

            ResponseEntity<Map> responseEntity = restTemplate.exchange(str, HttpMethod.GET, null, Map.class,map);
            pushDetailMap = (LinkedHashMap) responseEntity.getBody().get("result");
            logger.info("SEARCH REQIREMENT DETAIL CONNECT END++++++++++++++++++++++++++++");
            if(!responseEntity.getBody().get("code").toString().equals("0")){
                if(responseEntity.getBody().get("code").toString().equals("806")){
                    throw new CustomException(ResponseEnum.SHOP_NULL_ERROR);
                }
                logger.error("SEARCH REQIREMENT DETAIL ERRER++++++++++++++++++++++++++++");
                throw new CustomException(ResponseEnum.CNUIP_CONNECT_ERROR);
            }
        }catch (Exception e){
            if (e instanceof CustomException) {
                throw e;
            }
            logger.error("SEARCH REQIREMENT DETAIL ERRER:"+e);
            throw new CustomException(ResponseEnum.CNUIP_CONNECT_ERROR);
        }
        return pushDetailMap;
    }

    @Override
    public RequirementCountVo findRequirementCount(Long userId) {
        return requirementMapper.countRequirement(userId);
    }
}
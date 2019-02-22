package com.cnuip.colligate.service.impl;

import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.UserParam;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.ClientServiceUtils;
import com.cnuip.colligate.client.UserClient;
import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.exception.enums.ClientEnum;
import com.cnuip.colligate.service.CommentService;
import com.cnuip.colligate.vo.RequirementCountVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.github.pagehelper.PageInfo;
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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 王志斌
 * @Date: 2018/4/3 15:27
 */
@Service
public class CommentServiceImpl implements CommentService {

    private Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    @Value("${remote.cnuip.url}")
    private String cnuipUrl;

    @Override
    public ApiResponse shopList(Long userId, String isReply, int pageSize, int pageNum) throws ClientException {
        StringBuilder stringBuilder = new StringBuilder("/cnuip2-mservice-shop/v1/shop/comment/remote?");
        Map<String, Object> map = new HashMap<>();
        if (userId != null) {
            stringBuilder.append("remoteKey={remoteKey}&");
            map.put("remoteKey", userId);
        }
        if (isReply != null) {
            stringBuilder.append("isReply={isReply}&");
            map.put("isReply", isReply);
        }
        stringBuilder.append("pageSize={pageSize}&pageNumber={pageNumber}");
        map.put("pageSize", pageSize);
        map.put("pageNumber", pageNum);
        ApiResponse body = null;
        try {
            ResponseEntity<ApiResponse> entity = restTemplate.getForEntity(cnuipUrl + stringBuilder, ApiResponse.class, map);
            body = entity.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ClientException(ClientEnum.CNUIP_CLIENT_SHOP_ERROR);
        }
        return body;
    }

    @Override
    public ApiResponse reply(Long shopCommentId, String replyContent) throws ClientException {
        StringBuilder stringBuilder = new StringBuilder("/cnuip2-mservice-shop/v1/shop/comment/reply?shopCommentId={shopCommentId}&replyContent={replyContent}");
        Map<String, Object> map = new HashMap<>();
        map.put("shopCommentId", shopCommentId);
        map.put("replyContent", replyContent);
        ResponseEntity<ApiResponse> exchange = null;
        try {
            exchange = restTemplate.exchange(cnuipUrl + stringBuilder, HttpMethod.PUT, null, ApiResponse.class, map);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ClientException(ClientEnum.CNUIP_CLIENT_SHOP_ERROR);
        }
        return exchange.getBody();
    }

    @Override
    public ApiResponse goodsList(Long userId, String username, Long orgId, String isReply, int pageSize, int pageNum) throws ClientException {
        boolean isAdmin = false;
        StringBuilder url = new StringBuilder("/cnuip2-mservice-goods/v1/goods/comment/remote?");
        StringBuilder remoteKeys = new StringBuilder();
        if (username != null && "admin".equals(username)) {
            if (orgId == null) {
                throw new ClientException(ClientEnum.ORGANIZATION_ID_CAN_NOT_BE_NULL);
            }
            UserParam userParam = new UserParam();
            userParam.setUsername(username);
            userParam.setOrganizationId(orgId);
            userParam.setIsDelete(YesNoEnum.NO.toString());
            ApiResponse<PageInfo<User>> resUser = new ClientServiceUtils<PageInfo<User>, UserClient>().exec(userClient, "queryUser", userParam);
            if (resUser.getCode() != 0) {
                throw new ClientException(resUser.getCode(),resUser.getMessage());
            }
            List<User> list = resUser.getResult().getList();

            if (list.size() == 1) {
                Long id = list.get(0).getId();
                if (id.equals(userId)) {
                    isAdmin = true;
                }
            }
        }
        if (isAdmin) {
            UserParam userParam = new UserParam();
            userParam.setOrganizationId(orgId);
            userParam.setIsDelete(YesNoEnum.NO.toString());
            userParam.setPageNum(0);
            userParam.setPageSize(0);
            ApiResponse<PageInfo<User>> resUser = new ClientServiceUtils<PageInfo<User>, UserClient>().exec(userClient, "queryUser", userParam);
            List<User> list = resUser.getResult().getList();
            for (User user : list) {
                remoteKeys.append(user.getId()).append(",");
            }
            remoteKeys.deleteCharAt(remoteKeys.length() - 1);
        } else {
            remoteKeys.append(userId);
        }
        Map<String, Object> map = new HashMap<>();
        url.append("remoteKeys={remoteKeys}&");
        map.put("remoteKeys", remoteKeys);
        if (isReply != null) {
            url.append("isReply={isReply}&");
            map.put("isReply", isReply);
        }
        url.append("pageSize={pageSize}&pageNum={pageNum}");
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        ResponseEntity<ApiResponse> exchange = null;
        try {
            exchange = restTemplate.exchange(cnuipUrl + url, HttpMethod.GET, null, ApiResponse.class, map);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ClientException(ClientEnum.CNUIP_CLIENT_GOODS_ERROR);
        }
        return exchange.getBody();
    }

    @Override
    public ApiResponse replyGoods(String userId, Long goodsCommentId, String replyContent) throws ClientException {
        StringBuilder url = new StringBuilder("/cnuip2-mservice-goods/v1/goods/comment/reply?goodsCommentId={goodsCommentId}&replyContent={replyContent}");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Request-UserId", "1");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
        Map<String, Object> map = new HashMap<>();
        map.put("goodsCommentId", goodsCommentId);
        map.put("replyContent", replyContent);
        ResponseEntity<ApiResponse> exchange = null;
        try {
            exchange = restTemplate.exchange(cnuipUrl + url, HttpMethod.PUT, requestEntity, ApiResponse.class, map);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ClientException(ClientEnum.CNUIP_CLIENT_GOODS_ERROR);
        }
        if (exchange.getStatusCodeValue() != 200) {
            throw new ClientException(ClientEnum.CNUIP_CLIENT_GOODS_ERROR);
        } else {
            return exchange.getBody();
        }
    }

    @Override
    public RequirementCountVo findCommentCount(Long userId) throws ClientException {
        //查询店铺留言统计信息
        ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl+"cnuip2-mservice-shop/v1/shop/comment/count?remoteKey={remoteKey}", HttpMethod.GET,null,Map.class,userId);
        if((int)responseEntity.getBody().get("code") != 0){
            logger.error("SELECT COMMENTCOUNT INFOMATION ERROR"+responseEntity.getBody().get("message"));
            throw new ClientException(ClientEnum.CNUIP_CLIENT_SHOP_ERROR);
        }
        LinkedHashMap commentCount = (LinkedHashMap) responseEntity.getBody().get("result");
        //查询需求统计信息
        ApiResponse<RequirementCountVo> requirementCountRes = new ClientServiceUtils<RequirementCountVo, UserClient>().exec(userClient, "searchCount", userId);
        if(requirementCountRes.getCode() != 0){
            logger.error("SELECT REQUIREMENTCOUNT INFOMATION ERROR+++++++++++++++"+requirementCountRes.getMessage());
            throw new ClientException(ClientEnum.USER_ERROR + ":" + requirementCountRes.getMessage());
        }

        RequirementCountVo requirementCountVo = new RequirementCountVo();
        requirementCountVo.setCommentNum(((Integer) commentCount.get("commentNum")).longValue());
        requirementCountVo.setNoCommentReplyNum(((Integer)commentCount.get("noCommentReplyNum")).longValue());
        requirementCountVo.setRequirementNum(requirementCountRes.getResult().getRequirementNum());
        requirementCountVo.setNoRequirementReplyNum(requirementCountRes.getResult().getNoRequirementReplyNum());

        return requirementCountVo;
    }
}

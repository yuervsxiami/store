package com.cnuip.colligate.service.impl;

import com.cnuip.base.domain.params.UserParam;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.ClientServiceUtils;
import com.cnuip.base.utils.UUidUtils;
import com.cnuip.colligate.client.AuthorizeClient;
import com.cnuip.colligate.client.UserClient;
import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.exception.enums.ClientEnum;
import com.cnuip.colligate.service.AuthorizeService;
import com.cnuip.colligate.vo.AuthorizeExchange;
import com.cnuip.colligate.vo.AuthorizeModel;
import com.cnuip.colligate.vo.AuthorizeVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 王志斌
 * @Date: 2018/4/3 15:27
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Autowired
    private AuthorizeClient authorizeClient;

    @Autowired
    private UserClient userClient;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.cnuip.url}")
    private String syncUrl;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public AuthorizeVo addAuthorize(AuthorizeVo authorizeVo, Long userId) throws Exception {
        ApiResponse<User> userApiResponse = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryDetail", userId);
        if (userApiResponse.getCode() != 0) {
            throw new ClientException(userApiResponse.getCode(),userApiResponse.getMessage());
        }
        authorizeVo.setEditorName(userApiResponse.getResult().getRealName());
        ApiResponse<AuthorizeVo> authorize = new ClientServiceUtils<AuthorizeVo, AuthorizeClient>().exec(authorizeClient, "add", authorizeVo);
        //ApiResponse<AuthorizeVo> authorize = authorizeClient.add(authorizeVo);
        if (authorize.getCode() != 0) {
            throw new ClientException(authorize.getCode(),authorize.getMessage());
        }
        AuthorizeVo result = authorize.getResult();
        AuthorizeExchange authorizeExchange = new AuthorizeExchange();
        authorizeExchange.setRemoteKey(userId.toString());
        authorizeExchange.setRemoteId(String.valueOf(result.getId()));
        authorizeExchange.setAuthorizePatents(result.getAuthorizePatentList());
        String url = syncUrl + "/cnuip2-mservice-shop/v1/sender/authorize";
        try {
            ApiResponse apiResponse = restTemplate.postForObject(url, authorizeExchange, ApiResponse.class);
            if (apiResponse.getCode() != 0) {
                logger.error(ClientEnum.CNUIP_CLIENT_SEND_AUTHORIZE_ERROR.toString() + apiResponse);
                throw new ClientException(ClientEnum.CNUIP_CLIENT_SEND_AUTHORIZE_ERROR + "[" + apiResponse.getDetailMessage() + "]");
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ClientException(ClientEnum.CNUIP_CLIENT_SHOP_ERROR);
        }
        return result;
    }

    @Override
    public PageInfo<AuthorizeVo> list(Long userId, String username, Long orgId, AuthorizeModel authorizeModel) throws Exception {
        ApiResponse<PageInfo<AuthorizeVo>> search = new ClientServiceUtils<PageInfo<AuthorizeVo>, AuthorizeClient>().exec(authorizeClient, "search", userId, orgId, username, authorizeModel);
        //ApiResponse<PageInfo<AuthorizeVo>> authorize = authorizeClient.search(userId, username, authorizeModel);
        if (search.getCode() != 0) {
            throw new ClientException(search.getCode(),search.getMessage());
        } else {
            PageInfo<AuthorizeVo> result = search.getResult();
            for (AuthorizeVo one : result.getList()) {
                UserParam userParam = new UserParam();
                userParam.setUsername(one.getEditorName());
                one.setRealName(one.getEditorName());
            }
            return result;
        }
    }

    @Override
    public String getAuthorizeNum() throws Exception {
        return "WT"+ UUidUtils.getUUId(1);
    }
}

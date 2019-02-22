package com.cnuip.colligate.service;

import com.cnuip.colligate.vo.AuthorizeModel;
import com.cnuip.colligate.vo.AuthorizeVo;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface AuthorizeService {

    AuthorizeVo addAuthorize(AuthorizeVo authorizeVo, Long userId) throws Exception;

    PageInfo<AuthorizeVo> list(Long userId, String username, Long orgId, AuthorizeModel authorizeModel) throws Exception;

    String getAuthorizeNum() throws Exception;
}

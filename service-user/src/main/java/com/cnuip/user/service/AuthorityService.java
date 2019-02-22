package com.cnuip.user.service;

import com.cnuip.base.domain.params.AuthorityParam;
import com.cnuip.base.domain.user.Authority;
import com.cnuip.base.service.AbstractService;
import com.cnuip.user.vo.AuthorityVo;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface AuthorityService extends AbstractService<Authority, AuthorityParam> {
    List<AuthorityVo> tree(AuthorityParam authorityParam);
}

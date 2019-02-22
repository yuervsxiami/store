package com.cnuip.user.service.impl;

import com.cnuip.base.domain.params.RoleAuthorityParam;
import com.cnuip.base.domain.user.RoleAuthority;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.user.repository.RoleAuthorityMapper;
import com.cnuip.user.repository.base.RoleAuthorityBaseMapper;
import com.cnuip.user.service.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class RoleAuthorityServiceImpl
        extends AbstractServiceImpl<RoleAuthority, RoleAuthorityParam>
        implements RoleAuthorityService {

    @Autowired
    private RoleAuthorityBaseMapper roleAuthorityBaseMapper;

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;
}
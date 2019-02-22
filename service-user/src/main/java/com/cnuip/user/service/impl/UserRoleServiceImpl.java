package com.cnuip.user.service.impl;

import com.cnuip.base.domain.params.UserRoleParam;
import com.cnuip.base.domain.user.UserRole;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.user.repository.UserRoleMapper;
import com.cnuip.user.repository.base.UserRoleBaseMapper;
import com.cnuip.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class UserRoleServiceImpl
        extends AbstractServiceImpl<UserRole, UserRoleParam>
        implements UserRoleService {

    @Autowired
    private UserRoleBaseMapper userRoleBaseMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
}
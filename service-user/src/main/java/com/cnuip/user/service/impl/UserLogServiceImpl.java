package com.cnuip.user.service.impl;

import com.cnuip.base.domain.params.UserLogParam;
import com.cnuip.base.domain.user.UserLog;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.user.repository.UserLogMapper;
import com.cnuip.user.repository.base.UserLogBaseMapper;
import com.cnuip.user.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class UserLogServiceImpl
        extends AbstractServiceImpl<UserLog, UserLogParam>
        implements UserLogService {

    @Autowired
    private UserLogBaseMapper userLogBaseMapper;

    @Autowired
    private UserLogMapper userLogMapper;
}
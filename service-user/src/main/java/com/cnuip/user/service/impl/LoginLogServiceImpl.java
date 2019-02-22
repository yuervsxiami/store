package com.cnuip.user.service.impl;

import com.cnuip.base.domain.params.LoginLogParam;
import com.cnuip.base.domain.user.LoginLog;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.user.repository.LoginLogMapper;
import com.cnuip.user.repository.base.LoginLogBaseMapper;
import com.cnuip.user.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class LoginLogServiceImpl
        extends AbstractServiceImpl<LoginLog, LoginLogParam>
        implements LoginLogService {

    @Autowired
    private LoginLogBaseMapper loginLogBaseMapper;

    @Autowired
    private LoginLogMapper loginLogMapper;
}
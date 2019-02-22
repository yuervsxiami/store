package com.cnuip.authorize.service.impl;

import com.cnuip.authorize.repository.AuthorizePatentMapper;
import com.cnuip.authorize.repository.base.AuthorizePatentBaseMapper;
import com.cnuip.authorize.service.AuthorizePatentService;
import com.cnuip.base.domain.authorize.AuthorizePatent;
import com.cnuip.base.domain.params.AuthorizePatentParam;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xjt on 2018/8/30.
 */
@Service
public class AuthorizePatentServiceImpl extends AbstractServiceImpl<AuthorizePatent, AuthorizePatentParam> implements AuthorizePatentService {

    @Autowired
    private AuthorizePatentBaseMapper authorizePatentBaseMapper;

    @Autowired
    private AuthorizePatentMapper authorizePatentMapper;

}

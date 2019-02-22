package com.cnuip.user.service.impl;

import com.cnuip.base.domain.params.UserTeamParam;
import com.cnuip.base.domain.user.UserTeam;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.user.repository.UserTeamMapper;
import com.cnuip.user.repository.base.UserTeamBaseMapper;
import com.cnuip.user.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class UserTeamServiceImpl
        extends AbstractServiceImpl<UserTeam, UserTeamParam>
        implements UserTeamService {

    @Autowired
    private UserTeamBaseMapper userTeamBaseMapper;

    @Autowired
    private UserTeamMapper userTeamMapper;
}
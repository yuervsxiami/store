package com.cnuip.console.service.impl;

import com.cnuip.base.domain.console.Team;
import com.cnuip.base.domain.params.TeamParam;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.console.repository.TeamMapper;
import com.cnuip.console.repository.base.TeamBaseMapper;
import com.cnuip.console.rest.exceptions.CustomException;
import com.cnuip.console.rest.exceptions.ResponseEnum;
import com.cnuip.console.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class TeamServiceImpl extends AbstractServiceImpl<Team, TeamParam> implements TeamService {

    @Autowired
    private TeamBaseMapper teamBaseMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Team update(Team team) throws Exception {
        //检查项目组默认值
        String checkValue = team.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        this.checkTeam(team);
        teamBaseMapper.updateByKey(team.getId(), team);
        return teamBaseMapper.selectOneByKey(team.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Team addTeam(Team team) throws Exception{
        //检查项目组名称是否重复
        this.checkTeam(team);
        this.check(team);
        teamBaseMapper.insert(team);
        return teamBaseMapper.selectOneByKey(team.getId());
    }

    private void checkTeam(Team team) throws Exception{
        TeamParam teamParam = new TeamParam();
        teamParam.setOrganizationId(team.getOrganizationId());
        teamParam.setName(team.getName());
        Team newTeam = teamBaseMapper.selectOne(teamParam);
        if(newTeam != null){
            throw new CustomException(ResponseEnum.TEAM_EXIST_ERROR);
        }
    }
}
package com.cnuip.console.service;

import com.cnuip.base.domain.console.Team;
import com.cnuip.base.domain.params.TeamParam;
import com.cnuip.base.service.AbstractService;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface TeamService extends AbstractService<Team, TeamParam> {
    Team update(Team team) throws Exception;

    /**
     * 添加项目组
     * @param team
     * @return
     */
    Team addTeam(Team team) throws Exception;
}

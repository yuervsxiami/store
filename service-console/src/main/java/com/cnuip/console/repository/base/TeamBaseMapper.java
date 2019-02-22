package com.cnuip.console.repository.base;

import com.cnuip.base.domain.console.Team;
import com.cnuip.base.domain.params.TeamParam;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface TeamBaseMapper extends AbstractMapper<Team, TeamParam>
{}
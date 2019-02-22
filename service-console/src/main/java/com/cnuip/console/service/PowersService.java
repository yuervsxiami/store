package com.cnuip.console.service;

import com.cnuip.base.domain.console.Powers;
import com.cnuip.base.domain.params.PowersParam;
import com.cnuip.base.service.AbstractService;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface PowersService extends AbstractService<Powers, PowersParam> {

    Powers addPowers(Powers powers) throws Exception;

    Powers updatePowers(Powers powers) throws Exception;
}

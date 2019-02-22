package com.cnuip.user.service;

import com.cnuip.base.domain.params.RequirementParam;
import com.cnuip.base.domain.user.Requirement;
import com.cnuip.base.service.AbstractService;
import com.cnuip.user.vo.RequirementCountVo;
import com.cnuip.user.vo.param.RequirementQueryParam;

import java.util.LinkedHashMap;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface RequirementService extends AbstractService<Requirement, RequirementParam> {


    LinkedHashMap findRequirementList(Long userId, String username, RequirementQueryParam param) throws Exception;

    LinkedHashMap reply(Long userId, Long requirementId, String replyContent) throws Exception;

    LinkedHashMap findRequirementDetail(Long userId, Long requirementId) throws Exception;

    RequirementCountVo findRequirementCount(Long userId);
}

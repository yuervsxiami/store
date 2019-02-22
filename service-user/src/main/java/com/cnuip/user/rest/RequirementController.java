package com.cnuip.user.rest;

import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.user.service.RequirementService;
import com.cnuip.user.vo.RequirementCountVo;
import com.cnuip.user.vo.param.RequirementQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;

/**
 * @Author: 王志斌
 * @Date: 2018/10/16 10:39
 */
@RestController
@RequestMapping(value = "/v1/requirement")
@Api(value = "RequirementController", description = "需求接口")
public class RequirementController extends ControllerResponse {

    @Autowired
    private RequirementService requirementService;

    @GetMapping("/")
    @ApiOperation(notes = "需求列表", value = "需求列表", produces = "application/json")
    public ApiResponse<LinkedHashMap> findByCondition(@RequestHeader("X-Request-UserId") Long userId,
                                                      @RequestHeader("X-Request-UserName") String username,
                                                      RequirementQueryParam param) throws Exception{
        return ok(requirementService.findRequirementList(userId,username,param));
    }

    @GetMapping("/reply")
    @ApiOperation(notes = "回复需求", value = "回复需求", produces = "application/json")
    public ApiResponse<LinkedHashMap> reply(@RequestHeader("X-Request-UserId") String userId, Long requirementId, String replyContent) throws Exception {
        return ok(requirementService.reply(Long.valueOf(userId), requirementId, replyContent));
    }

    @GetMapping("/detail")
    @ApiOperation(notes = "需求详情", value = "需求详情", produces = "application/json")
    public ApiResponse<LinkedHashMap> findRequirementDetail(@RequestHeader("X-Request-UserId") Long userId, Long requirementId) throws Exception{
        return ok(requirementService.findRequirementDetail(userId,requirementId));
    }

    @ApiIgnore
    @GetMapping("/count")
    @ApiOperation(notes = "需求统计", value = "需求统计", produces = "application/json")
    public ApiResponse<RequirementCountVo> findRequirementCount(@RequestParam Long userId) throws Exception{
        return ok(requirementService.findRequirementCount(userId));
    }
}
package com.cnuip.colligate.rest;

import com.cnuip.colligate.service.ProcessService;
import com.cnuip.colligate.vo.ProcessVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/process")
@Api(value = "ProcessController", description = "提案接口")
public class ProcessController extends ControllerResponse {

    @Autowired
    private ProcessService processService;

    @PostMapping("/")
    @ApiOperation(notes = "新增提案", value = "新增提案", produces = "application/json")
    public ApiResponse<ProcessVo> add(@RequestHeader("X-Request-UserId") Long editorId,
                                      @RequestHeader("X-Request-OrganizationId") Long orgId,
                                      @RequestBody ProcessVo processVo) throws Exception {
        return ok(processService.addProcess(editorId,orgId,processVo));
    }

}

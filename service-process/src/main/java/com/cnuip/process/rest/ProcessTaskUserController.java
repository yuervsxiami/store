package com.cnuip.process.rest;

import com.cnuip.base.domain.params.ProcessTaskUserParam;
import com.cnuip.base.domain.process.ProcessTaskUser;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.process.service.ProcessTaskUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/process/task/user")
@Api(value = "ProcessTaskUserController", description = "提案环节审核人员接口")
public class ProcessTaskUserController extends ControllerResponse {

    @Autowired
    private ProcessTaskUserService processTaskUserService;

    @PostMapping("/")
    @ApiOperation(notes = "新增提案环节审核人", value = "新增提案环节审核人", produces = "application/json")
    public ApiResponse<ProcessTaskUser> add(@RequestBody ProcessTaskUser processTaskUser) throws Exception {
        processTaskUserService.check(processTaskUser);
        return ok(processTaskUserService.insert(processTaskUser));
    }

    @PutMapping("/")
    @ApiOperation(notes = "修改提案环节审核状态", value = "修改提案环节审核状态", produces = "application/json")
    public ApiResponse<ProcessTaskUser> update(@RequestHeader("X-Request-UserId") Long examinId,
                                               @RequestBody ProcessTaskUser processTaskUser) throws Exception {
        processTaskUser.setExaminId(examinId);
        return ok(processTaskUserService.updateProcessTaskState(processTaskUser));
    }

    @GetMapping("/detail")
    @ApiOperation(notes = "查看提案环节审核详情", value = "查看提案环节审核详情", produces = "application/json")
    public ApiResponse<ProcessTaskUser> selectProcessTaskDetail(ProcessTaskUserParam processTaskUserParam) throws Exception {
        return ok(processTaskUserService.selectOne(processTaskUserParam));
    }

    @GetMapping("/")
    @ApiOperation(notes = "查找提案环节", value = "查找提案环节", produces = "application/json")
    public ApiResponse<ProcessTaskUser> search(ProcessTaskUserParam processTaskUserParam) throws Exception {
        return ok(processTaskUserService.selectOne(processTaskUserParam));
    }

}

package com.cnuip.process.rest;

import com.cnuip.base.domain.process.ProcessTask;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.process.service.ProcessTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/process/task")
@Api(value = "ProcessTaskController", description = "提案环节接口")
public class ProcessTaskController extends ControllerResponse {

    @Autowired
    private ProcessTaskService processTaskService;


    @GetMapping("/select")
    @ApiOperation(notes = "根据流程环节ID查询提案环节接口", value = "根据流程环节ID查询提案环节接口", produces = "application/json")
    public ApiResponse<ProcessTask> selectProcessTask(Long tmplProcessTaskId){
        return ok(processTaskService.selectOneByField("tmpl_process_task_id",tmplProcessTaskId,null));
    }

    @GetMapping("/")
    @ApiOperation(notes = "根据提案ID和流程环节ID查询提案环节", value = "根据提案ID和流程环节ID查询提案环节", produces = "application/json")
    public ApiResponse<ProcessTask> queryProcessTask(Long processId,Long tmplProcessTaskId) throws Exception{
        return ok(processTaskService.queryProcessTask(processId,tmplProcessTaskId));
    }
}

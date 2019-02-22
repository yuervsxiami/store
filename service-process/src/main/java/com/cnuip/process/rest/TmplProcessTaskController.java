package com.cnuip.process.rest;

import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.process.service.TmplProcessTaskService;
import com.cnuip.process.vo.TmplProcessTaskVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/tmplprocess/task")
@Api(value = "TmplProcessTaskController", description = "流程环节模板接口")
public class TmplProcessTaskController extends ControllerResponse {

    @Autowired
    private TmplProcessTaskService tmplProcessTaskService;

    @PostMapping("/")
    @ApiOperation(notes = "新增流程环节模板", value = "新增流程环节模板", produces = "application/json")
    public ApiResponse<TmplProcessTaskVo> add(@RequestHeader("X-Request-UserId") Long editorId,
                                              @RequestHeader("X-Request-UserName") String editorName,
                                              @RequestBody TmplProcessTaskVo tmplProcessTaskVo) throws Exception {
        tmplProcessTaskVo.setEditorId(editorId);
        tmplProcessTaskVo.setEditorName(editorName);
        return ok(tmplProcessTaskService.addTmplProcessTask(tmplProcessTaskVo));
    }

    @PutMapping("/")
    @ApiOperation(notes = "修改流程环节模板", value = "修改流程环节模板", produces = "application/json")
    public ApiResponse<TmplProcessTaskVo> update(@RequestBody TmplProcessTaskVo tmplProcessTaskVo) throws Exception {
        return ok(tmplProcessTaskService.updateTmplProcessTask(tmplProcessTaskVo));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除流程环节模板", value = "删除流程环节模板", produces = "application/json")
    public ApiResponse<TmplProcessTaskVo> delete(@RequestParam Long tmplProcessTaskId) throws Exception {
        return ok(tmplProcessTaskService.deleteTmplProcessTask(tmplProcessTaskId));
    }

    @GetMapping("/detail")
    @ApiOperation(notes = "查看流程环节模板列表详情", value = "查看流程环节模板列表详情", produces = "application/json")
    public ApiResponse<List<TmplProcessTaskVo>> selectTmplProcessTaskDetail(@RequestParam Long tmplProcessId) throws Exception {
        return ok(tmplProcessTaskService.selectTmplProcessTaskDetail(tmplProcessId));
    }
}

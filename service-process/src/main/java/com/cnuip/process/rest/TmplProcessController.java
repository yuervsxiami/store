package com.cnuip.process.rest;

import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.TmplProcessParam;
import com.cnuip.base.domain.process.TmplProcess;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.process.service.TmplProcessService;
import com.cnuip.process.vo.TmplProcessVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/tmplprocess")
@Api(value = "TmplProcessController", description = "流程模板接口")
public class TmplProcessController extends ControllerResponse {

    @Autowired
    private TmplProcessService tmplProcessService;

    @PostMapping("/")
    @ApiOperation(notes = "新增流程模板", value = "新增流程模板", produces = "application/json")
    public ApiResponse<TmplProcessVo> add(@RequestHeader("X-Request-UserId") Long editorId,
                                          @RequestHeader("X-Request-UserName") String editorName,
                                          @RequestHeader("X-Request-OrganizationId") Long orgId,
                                          @RequestBody TmplProcessVo tmplProcessVo) throws Exception {
        tmplProcessVo.setEditorId(editorId);
        tmplProcessVo.setEditorName(editorName);
        tmplProcessVo.setOrganizationId(orgId);
        return ok(tmplProcessService.addTmplProcess(tmplProcessVo));
    }


    @PutMapping("/")
    @ApiOperation(notes = "修改流程模板", value = "修改流程模板", produces = "application/json")
    public ApiResponse<TmplProcessVo> update(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                             @RequestBody TmplProcessVo tmplProcessVo) throws Exception {
        tmplProcessVo.setOrganizationId(orgId);
        return ok(tmplProcessService.updateTmplProcess(tmplProcessVo));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除流程模板", value = "删除流程模板", produces = "application/json")
    public ApiResponse<TmplProcess> delete(@RequestParam Long tmplProcessId) throws Exception {
        tmplProcessService.updateFieldByKey(tmplProcessId,"isDelete", YesNoEnum.YES);
        return ok(null);
    }

    @GetMapping("/detail")
    @ApiOperation(notes = "查看流程模板详情", value = "查看流程模板详情", produces = "application/json")
    public ApiResponse<TmplProcessVo> selectTmplProcessDetail(@RequestParam Long tmplProcessId) throws Exception {
        return ok(tmplProcessService.selectTmplProcessDetail(tmplProcessId));
    }

    @GetMapping("/")
    @ApiOperation(notes = "查询流程模板列表", value = "查询流程模板列表", produces = "application/json")
    public ApiResponse<List<TmplProcess>> selectTmplProcess(@RequestHeader("X-Request-OrganizationId") Long orgId){
        TmplProcessParam tmplProcessParam = new TmplProcessParam();
        tmplProcessParam.setIsDelete(YesNoEnum.NO.name());
        tmplProcessParam.setOrganizationId(orgId);
        return ok(tmplProcessService.getAll(tmplProcessParam));
    }

}

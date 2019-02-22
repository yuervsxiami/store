package com.cnuip.result.rest;

import com.cnuip.base.domain.params.LabelParam;
import com.cnuip.base.domain.result.Label;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.result.service.LabelService;
import com.cnuip.result.vo.LabelVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/label")
@Api(value = "LabelController", description = "成果标签接口")
public class LabelController extends ControllerResponse {

    @Autowired
    private LabelService labelService;

    @PostMapping("/")
    @ApiOperation(notes = "新增成果标签", value = "新增成果标签", produces = "application/json")
    public ApiResponse<Label> add(@RequestHeader("X-Request-UserId") Long editorId,
                                  @RequestHeader("X-Request-UserName") String editorName,
                                  @RequestHeader("X-Request-OrganizationId") Long orgId,
                                  @RequestBody Label label) throws Exception {
        label.setEditorId(editorId);
        label.setEditorName(editorName);
        label.setOrganizationId(orgId);
        return ok(labelService.addLabel(label));
    }

    @GetMapping("/")
    @ApiOperation(notes = "成果标签列表", value = "成果标签列表", produces = "application/json")
    public ApiResponse<PageInfo<LabelVo>> search(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                 LabelParam labelParam) throws Exception {
        labelParam.setOrganizationId(orgId);
        return ok(labelService.selectLabel(labelParam));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除成果标签", value = "删除成果标签", produces = "application/json")
    public ApiResponse<LabelVo> delete(@RequestParam("labelId") long labelId) throws Exception {
        return ok(labelService.deleteLabel(labelId));
    }

    @PutMapping("/")
    @ApiOperation(notes = "修改成果标签", value = "修改成果标签", produces = "application/json")
    public ApiResponse<Label> selectLabelDetail(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                @RequestBody Label label) throws Exception {
        label.setOrganizationId(orgId);
        //检查成果标签默认值
        String checkValue = label.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        labelService.updateByKey(label.getId(),label);
        return ok(label);
    }
}

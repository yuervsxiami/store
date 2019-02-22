package com.cnuip.result.rest;

import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.LabelValueParam;
import com.cnuip.base.domain.result.LabelValue;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.result.service.LabelValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/labelvalue")
@Api(value = "LabelValueController", description = "成果标签值接口")
public class LabelValueController extends ControllerResponse {

    @Autowired
    private LabelValueService labelValueService;

    @PostMapping("/")
    @ApiOperation(notes = "新增成果标签值", value = "新增成果标签值", produces = "application/json")
    public ApiResponse<LabelValue> add(@RequestHeader("X-Request-UserId") Long editorId,
                                       @RequestHeader("X-Request-UserName") String editorName,
                                       @RequestBody LabelValue labelValue) throws Exception {
        labelValue.setEditorId(editorId);
        labelValue.setEditorName(editorName);
        labelValueService.check(labelValue);
        return ok(labelValueService.insert(labelValue));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除成果标签值", value = "删除成果标签值", produces = "application/json")
    public ApiResponse<LabelValue> delete(@RequestParam("labelValueId") long labelValueId) throws Exception {
        return ok(labelValueService.updateFieldByKey(labelValueId,"isDelete", YesNoEnum.YES));
    }

    @GetMapping("/")
    @ApiOperation(notes = "成果标签值列表", value = "根据标签ID查询成果标签值列表", produces = "application/json")
    public ApiResponse<List<LabelValue>> search(@RequestParam("labelId") long labelId) throws Exception {
        LabelValueParam labelValueParam = new LabelValueParam();
        labelValueParam.setLabelId(labelId);
        labelValueParam.setIsDelete(YesNoEnum.NO.name());
        return ok(labelValueService.getAll(labelValueParam));
    }

    @PutMapping("/")
    @ApiOperation(notes = "修改成果标签值", value = "修改成果标签值", produces = "application/json")
    public ApiResponse<LabelValue> search(@RequestBody LabelValue labelValue) throws Exception {
        return ok(labelValueService.updateByKey(labelValue.getId(),labelValue));
    }

}

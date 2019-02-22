package com.cnuip.colligate.rest;


import com.cnuip.colligate.service.PatentResultService;
import com.cnuip.colligate.vo.PatentResultVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/patentresult")
@Api(value = "PatentResultController", description = "专利成果接口")
public class PatentResultController extends ControllerResponse {

    @Autowired
    private PatentResultService patentResultService;

    @PostMapping("/")
    @ApiOperation(notes = "新增专利成果", value = "新增专利成果", produces = "application/json")
    public ApiResponse<PatentResultVo> add(@RequestHeader("X-Request-UserId") Long editorId,
                                           @RequestHeader("X-Request-OrganizationId") Long orgId,
                                           @RequestBody PatentResultVo patentResultVo) throws Exception {
        patentResultVo.setEditorId(editorId);
        patentResultVo.setOrganizationId(orgId);
        return ok(patentResultService.addPatentResult(patentResultVo));
    }
}

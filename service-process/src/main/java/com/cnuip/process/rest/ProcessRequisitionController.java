package com.cnuip.process.rest;

import com.cnuip.base.domain.process.ProcessRequisition;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.process.service.ProcessRequisitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/process/requisition")
@Api(value = "ProcessRequisitionController", description = "提案申请书接口")
public class ProcessRequisitionController extends ControllerResponse {

    @Autowired
    private ProcessRequisitionService processService;

    @PostMapping("/")
    @ApiOperation(notes = "新增提案申请书", value = "新增提案申请书", produces = "application/json")
    public ApiResponse<List<ProcessRequisition>> add(@RequestBody List<ProcessRequisition> processRequisitionList) throws Exception {
        return ok(processService.addProcessRequisition(processRequisitionList));
    }
}

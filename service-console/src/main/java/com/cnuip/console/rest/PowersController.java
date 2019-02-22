package com.cnuip.console.rest;


import com.cnuip.base.domain.console.Powers;
import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.PowersParam;
import com.cnuip.console.service.PowersService;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/powers")
@Api(value = "PowersController", description = "职权接口")
public class PowersController extends ControllerResponse {

    @Autowired
    private PowersService powersService;

    @PostMapping("/")
    @ApiOperation(notes = "新增职权", value = "新增职权", produces = "application/json")
    public ApiResponse<Powers> add(@RequestHeader("X-Request-UserId") Long editorId,
                                   @RequestHeader("X-Request-UserName") String editorName,
                                   @RequestHeader("X-Request-OrganizationId") Long orgId,
                                   @RequestBody Powers powers) throws Exception {
        powers.setOrganizationId(orgId);
        powers.setEditorId(editorId);
        powers.setEditorName(editorName);
        return ok(powersService.addPowers(powers));
    }


    @PutMapping("/")
    @ApiOperation(notes = "修改职权", value = "修改职权", produces = "application/json")
    public ApiResponse<Powers> update(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                      @RequestBody Powers powers) throws Exception {
        if(powers.getOrganizationId() == null){
            powers.setOrganizationId(orgId);
        }
        return ok(powersService.updatePowers(powers));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除职权", value = "删除职权", produces = "application/json")
    public ApiResponse<Powers> delete(@RequestParam("id") Long id) throws Exception {
        return ok(powersService.updateFieldByKey(id, "isDelete", YesNoEnum.YES));
    }

    @GetMapping("/")
    @ApiOperation(notes = "职权列表", value = "职权列表", produces = "application/json")
    public ApiResponse<PageInfo<Powers>> search(@RequestHeader("X-Request-OrganizationId") Long orgId, PowersParam powersParam) throws Exception {
        powersParam.setOrganizationId(orgId);
        return ok(powersService.selectMany(powersParam));
    }
}

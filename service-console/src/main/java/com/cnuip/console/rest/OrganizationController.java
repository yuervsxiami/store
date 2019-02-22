package com.cnuip.console.rest;


import com.cnuip.base.domain.console.Organization;
import com.cnuip.base.domain.params.OrganizationParam;
import com.cnuip.console.service.OrganizationService;
import com.cnuip.console.vo.OrganizationVo;
import com.cnuip.console.vo.ProvinceOrganizationVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/org")
@Api(value = "OrganizationController", description = "组织接口")
public class OrganizationController extends ControllerResponse {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/all")
    @ApiOperation(notes = "获取全部组织列表", value = "获取全部组织列表", produces = "application/json")
    public ApiResponse<List<OrganizationVo>> getAll(OrganizationParam organizationParam) {
        return ok(organizationService.getAllVo(organizationParam));
    }

    @PostMapping("/")
    @ApiOperation(notes = "新增组织", value = "新增组织", produces = "application/json")
    public ApiResponse<Organization> add(@RequestHeader("X-Request-UserId") Long editorId,
                                         @RequestHeader("X-Request-UserName") String editorName,
                                         @RequestBody OrganizationVo organization) throws Exception {
        organization.setEditorId(editorId);
        organization.setEditorName(editorName);
        return ok(organizationService.addOrganization(organization));
    }

    @PutMapping("/base")
    @ApiOperation(notes = "更新组织信息（该接口仅内部使用，外部调用接口在colligate中）", value = "更新组织信息（该接口仅内部使用，外部调用接口在colligate中）", produces = "application/json")
    public ApiResponse<Organization> updateOrg(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                 @RequestBody OrganizationVo organization) throws Exception {
        if (organization.getId() == null) {
            organization.setId(orgId);
        }
        return ok(organizationService.updateVo(organization));
    }

    @GetMapping("/detail")
    @ApiOperation(notes = "获取组织信息详情", value = "获取组织信息详情", produces = "application/json")
    public ApiResponse<OrganizationVo> detail(@RequestHeader("X-Request-OrganizationId") Long orgId) {
        return ok(organizationService.selectVo(orgId));
    }

    @GetMapping("/province/organization")
    @ApiOperation(notes = "获取省份组织树", value = "获取省份组织树", produces = "application/json")
    public ApiResponse<List<ProvinceOrganizationVo>> provinceOrganizationTree() {
        return ok(organizationService.getProvinceOrganizationTree());
    }

}

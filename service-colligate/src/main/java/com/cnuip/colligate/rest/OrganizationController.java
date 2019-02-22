package com.cnuip.colligate.rest;

import com.cnuip.base.domain.console.Organization;
import com.cnuip.colligate.service.OrganizationService;
import com.cnuip.colligate.vo.OrganizationVo;
import com.cnuip.colligate.vo.domain.Shop;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/org")
@Api(value = "OrganizationController", description = "组织接口")
public class OrganizationController extends ControllerResponse {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/")
    @ApiOperation(notes = "新增组织(cnuip2新增专利宝店铺后台调用)", value = "新增组织(cnuip2新增专利宝店铺后台调用)", produces = "application/json")
    public ApiResponse<Organization> add(@RequestHeader("X-Request-UserId") Long editorId,
                                         @RequestHeader("X-Request-UserName") String editorName,
                                         @RequestBody Shop shop) throws Exception {
        return ok(organizationService.addOrganization(editorId,editorName,shop));
    }

    @PutMapping("/")
    @ApiOperation(notes = "更新组织信息", value = "更新组织信息", produces = "application/json")
    public ApiResponse<Organization> updateOrg(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                               @RequestBody OrganizationVo organization) throws Exception {
        if (organization.getId() == null) {
            organization.setId(orgId);
        }
        return ok(organizationService.updateOrg(organization));
    }

}

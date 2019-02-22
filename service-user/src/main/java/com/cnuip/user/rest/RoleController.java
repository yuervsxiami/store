package com.cnuip.user.rest;


import com.cnuip.base.domain.params.RoleParam;
import com.cnuip.base.domain.user.Role;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.user.service.RoleService;
import com.cnuip.user.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/role")
@Api(value = "RoleController", description = "管理组接口")
public class RoleController extends ControllerResponse {

    @Autowired
    private RoleService roleService;


    @PostMapping("/")
    public ApiResponse<Role> addRole(@RequestHeader("X-Request-UserId") Long editorId,
                                     @RequestHeader("X-Request-UserName") String editorName,
                                     @RequestHeader("X-Request-OrganizationId") Long orgId,
                                     @RequestBody RoleVo roleVo) throws Exception {
        roleVo.setEditorId(editorId);
        roleVo.setEditorName(editorName);
        roleVo.setOrganizationId(orgId);
        return ok(roleService.addRole(roleService.check(roleVo)));
    }

    @DeleteMapping("/")
    public ApiResponse<String> deleteRole(@RequestParam Long roleId) throws Exception {
        return ok(roleService.deleteRole(roleId));
    }

    @GetMapping("/")
    public ApiResponse<PageInfo<RoleVo>> queryRole(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                   RoleParam roleParam) throws Exception {
        roleParam.setOrganizationId(orgId);
        return ok(roleService.queryRole(roleParam));
    }

    @PutMapping("/")
    public ApiResponse<RoleVo> updateRole(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                          @RequestBody RoleVo roleVo) throws Exception {
        roleVo.setOrganizationId(orgId);
        //检查角色默认值
        String checkValue = roleVo.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        return ok(roleService.updateRole(roleVo));
    }

    @GetMapping("/detail")
    public ApiResponse<RoleVo> queryDetail(@RequestParam Long id) {
        return ok(roleService.queryDetail(roleService.selectOneByKey(id)));
    }
}

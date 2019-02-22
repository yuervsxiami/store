package com.cnuip.console.rest;

import com.cnuip.base.domain.console.Department;
import com.cnuip.console.service.DepartmentService;
import com.cnuip.console.vo.DepartmentVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/department")
@Api(value = "DepartmentController", description = "部门接口")
public class DepartmentController extends ControllerResponse {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    @ApiOperation(notes = "新增部门", value = "新增部门", produces = "application/json")
    public ApiResponse<Department> add(@RequestHeader("X-Request-UserId") Long editorId,
                                       @RequestHeader("X-Request-UserName") String editorName,
                                       @RequestHeader("X-Request-OrganizationId") Long orgId,
                                       @RequestBody Department department) throws Exception {
        department.setOrganizationId(orgId);
        department.setEditorId(editorId);
        department.setEditorName(editorName);
        return ok(departmentService.addDepartment(department));
    }


    @PutMapping("/")
    @ApiOperation(notes = "修改部门", value = "修改部门", produces = "application/json")
    public ApiResponse<Department> update(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                          @RequestBody Department department) throws Exception {
        if (department.getOrganizationId() == null) {
            department.setOrganizationId(orgId);
        }
        return ok(departmentService.updateDepartment(department));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除部门", value = "删除部门", produces = "application/json")
    public ApiResponse<Department> delete(@RequestParam("id") Long id) throws Exception {
        return ok(departmentService.deleteDept(id));
    }

    @GetMapping("/")
    @ApiOperation(notes = "部门列表(树状表)", value = "部门列表(树状表)", produces = "application/json")
    public ApiResponse<List<DepartmentVo>> searchTree(@RequestHeader("X-Request-OrganizationId") Long organizationId) throws Exception {
        return ok(departmentService.getAllDepartmentTree(organizationId));
    }

    @GetMapping("/list")
    @ApiOperation(notes = "部门列表", value = "部门列表", produces = "application/json")
    public ApiResponse<List<Department>> searchList(@RequestHeader("X-Request-OrganizationId") Long organizationId, @RequestParam(required = false) String keyWords) throws Exception {
        return ok(departmentService.getAllDepartmentList(organizationId, keyWords));
    }

    @ApiIgnore
    @PostMapping("/base/examine")
    public ApiResponse<List<Department>> baseListExamine(@RequestBody List<Long> deptIds) throws Exception{
        return ok(departmentService.getExamineList(deptIds));
    }
}

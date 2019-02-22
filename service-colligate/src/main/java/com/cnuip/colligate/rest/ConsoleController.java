package com.cnuip.colligate.rest;

import com.cnuip.base.domain.console.Department;
import com.cnuip.base.domain.console.Post;
import com.cnuip.base.domain.console.Powers;
import com.cnuip.colligate.service.ConsoleService;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/colligate/console")
@Api(value = "ConsoleController", description = "Console模块接口")
public class ConsoleController extends ControllerResponse {
    @Autowired
    private ConsoleService consoleService;

    @GetMapping("/dept")
    @ApiOperation(notes = "获取部门树(审核时)", value = "获取部门树(审核时)", produces = "application/json")
    public ApiResponse<List<Department>> getTreeExamine(@RequestHeader("X-Request-OrganizationId") Long organizationId,
                                                        @RequestParam Long powersId) throws Exception {
        return ok(consoleService.getTreeExamine(organizationId, powersId));
    }

    @DeleteMapping("/department")
    @ApiOperation(notes = "删除部门", value = "删除部门", produces = "application/json")
    ApiResponse<Department> delete(@RequestParam("id") Long id) throws Exception{
        return ok(consoleService.deleteDept(id));
    }

    @DeleteMapping("/powers")
    @ApiOperation(notes = "删除职权", value = "删除职权", produces = "application/json")
    public ApiResponse<Powers> deletePowers(@RequestParam("id") Long id) throws Exception{
        return ok(consoleService.deletePowers(id));
    }

    @DeleteMapping("/post")
    @ApiOperation(notes = "删除岗位", value = "删除岗位", produces = "application/json")
    public ApiResponse<Post> deletePost(@RequestParam("id") Long id) throws Exception {
        return ok(consoleService.deletePost(id));
    }
}

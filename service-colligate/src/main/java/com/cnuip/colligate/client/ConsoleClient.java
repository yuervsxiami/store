package com.cnuip.colligate.client;

import com.cnuip.base.domain.console.Department;
import com.cnuip.base.domain.console.Organization;
import com.cnuip.base.domain.console.Post;
import com.cnuip.base.domain.console.Powers;
import com.cnuip.colligate.vo.DepartmentVo;
import com.cnuip.colligate.vo.OrganizationVo;
import com.cnuip.gaea.service.web.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "store2-service-console")
public interface ConsoleClient {
    @GetMapping("/v1/department/")
    ApiResponse<List<DepartmentVo>> searchTree(@RequestHeader("X-Request-OrganizationId") Long organizationId);

    @GetMapping("/v1/org/detail")
    ApiResponse<Organization> queryDetail(@RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception;

    @PostMapping("/v1/org/")
    ApiResponse<Organization> add(@RequestHeader("X-Request-UserId") Long editorId,
                                  @RequestHeader("X-Request-UserName") String editorName,
                                  @RequestBody OrganizationVo organization) throws Exception;

    @PutMapping("/v1/org/base")
    @ApiOperation(notes = "更新组织信息", value = "更新组织信息", produces = "application/json")
    ApiResponse<Organization> updateOrg(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                        @RequestBody OrganizationVo organization) throws Exception;

    @PostMapping("/v1/department/base/examine")
    ApiResponse<List<Department>> baseListExamine(@RequestBody List<Long> deptIds);

    @DeleteMapping("v1/department/")
    @ApiOperation(notes = "删除部门", value = "删除部门", produces = "application/json")
    ApiResponse<Department> delete(@RequestParam("id") Long id) throws Exception;


    @DeleteMapping("/v1/powers/")
    @ApiOperation(notes = "删除职权", value = "删除职权", produces = "application/json")
    ApiResponse<Powers> deletePowers(@RequestParam("id") Long id) throws Exception;

    @DeleteMapping("/v1/post/")
    @ApiOperation(notes = "删除岗位", value = "删除岗位", produces = "application/json")
    ApiResponse<Post> deletePost(@RequestParam("id") Long id);
}

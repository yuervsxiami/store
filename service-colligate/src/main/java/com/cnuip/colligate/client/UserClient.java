package com.cnuip.colligate.client;

import com.cnuip.base.domain.params.AuthorityParam;
import com.cnuip.base.domain.params.UserParam;
import com.cnuip.base.domain.user.Role;
import com.cnuip.base.domain.user.User;
import com.cnuip.colligate.vo.AuthorityVo;
import com.cnuip.colligate.vo.RequirementCountVo;
import com.cnuip.colligate.vo.RoleVo;
import com.cnuip.colligate.vo.UserVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "store2-service-user")
public interface UserClient {

    @GetMapping("/v1/user/detail")
    ApiResponse<User> queryDetail(@RequestParam(value = "id") Long userId) throws Exception;

    @PostMapping("/v1/user/base/dept/usercount")
    ApiResponse<PageInfo<User>> queryUser(@RequestBody UserParam userParam) throws Exception;

    @GetMapping("/v1/user/admin")
    ApiResponse<User> queryAdminUser(@RequestParam(value = "organizationId") Long organizationId) throws Exception;

    @GetMapping("/v1/user/base/dept")
    ApiResponse<PageInfo<User>> queryForBase(@RequestParam(value = "deptId") Long deptId, @RequestParam(value = "powersId") Long powersId) throws Exception;

    @PostMapping("/v1/user/")
    @ApiOperation(notes = "新增用户", value = "新增用户", produces = "application/json")
    ApiResponse<User> add(@RequestHeader("X-Request-UserId") Long editorId,
                          @RequestHeader("X-Request-UserName") String editorName,
                          @RequestHeader("X-Request-OrganizationId") Long orgId,
                          @RequestHeader("X-Request-OrganizationName") String orgName,
                          @RequestBody UserVo userVo) throws Exception;

    @PostMapping("/v1/role/")
    ApiResponse<Role> addRole(@RequestHeader("X-Request-UserId") Long editorId,
                              @RequestHeader("X-Request-UserName") String editorName,
                              @RequestHeader("X-Request-OrganizationId") Long orgId,
                              @RequestBody RoleVo roleVo) throws Exception;

    @PostMapping("/v1/authority/base")
    ApiResponse<List<AuthorityVo>> queryAuth(AuthorityParam authorityParam) throws Exception;

    @GetMapping("/v1/user/base/dept/examine")
    ApiResponse<List<Long>> queryDeptIdBase(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                            @RequestParam("powersId") Long powersId);

    @GetMapping("/v1/requirement/count")
    @ApiOperation(notes = "需求统计", value = "需求统计", produces = "application/json")
    ApiResponse<RequirementCountVo> searchCount(@RequestParam(value = "userId") Long userId);
}

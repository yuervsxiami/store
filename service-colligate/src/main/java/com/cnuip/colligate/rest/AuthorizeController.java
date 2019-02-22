package com.cnuip.colligate.rest;


import com.cnuip.colligate.service.AuthorizeService;
import com.cnuip.colligate.vo.AuthorizeModel;
import com.cnuip.colligate.vo.AuthorizeVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/colligate/authorize")
@Api(value = "AuthorizeController", description = "委托接口")
public class AuthorizeController extends ControllerResponse {

    @Autowired
    private AuthorizeService authorizeService;

    @PostMapping("/")
    @ApiOperation(notes = "新增专利委托", value = "新增专利委托", produces = "application/json")
    public ApiResponse<AuthorizeVo> add(@RequestHeader("X-Request-UserId") Long userId,
                                        @RequestHeader("X-Request-UserName") String username,
                                        @RequestHeader("X-Request-OrganizationId") Long orgId,
                                        @RequestBody AuthorizeVo authorizeVo) throws Exception {
        authorizeVo.setOrganizationId(orgId);
        authorizeVo.setEditorId(userId);
        return ok(authorizeService.addAuthorize(authorizeVo, userId));
    }

    @GetMapping("/")
    @ApiOperation(notes = "查询专利委托列表", value = "查询专利委托列表", produces = "application/json")
    ApiResponse<PageInfo<AuthorizeVo>> searchList(@RequestHeader("X-Request-UserId") Long userId,
                                                  @RequestHeader("X-Request-UserName") String username,
                                                  @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                  AuthorizeModel authorizeModel) throws Exception {
        return ok(authorizeService.list(userId, username,orgId, authorizeModel));
    }

    @GetMapping("/authorizeNum")
    @ApiOperation(notes = "获取委托唯一默认编号", value = "获取委托唯一默认编号", produces = "application/json")
    ApiResponse<String> getAuthorizeNum() throws Exception {
        return ok(authorizeService.getAuthorizeNum());
    }

}

package com.cnuip.authorize.rest;

import com.cnuip.authorize.vo.AuthorizeModel;
import com.cnuip.authorize.vo.AuthorizeVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/authorize")
@Api(value = "AuthorizeController", description = "专利委托接口")
public class AuthorizeController extends ControllerResponse {

    @Autowired
    private com.cnuip.authorize.service.AuthorizeService AuthorizeService;

    @ApiIgnore
    @PostMapping("/")
    @ApiOperation(notes = "新增专利委托", value = "新增专利委托", produces = "application/json")
    public ApiResponse<AuthorizeVo> add(@RequestBody AuthorizeVo authorizeVo) throws Exception {
        return ok(AuthorizeService.addAuthorize(authorizeVo));
    }

//    @GetMapping("/")
//    @ApiOperation(notes = "专利委托列表", value = "专利委托列表", produces = "application/json")
//    public ApiResponse<PageInfo<AuthorizeVo>> search(@RequestHeader("X-Request-UserId") Long userId,
//                                                     @RequestHeader("X-Request-UserName") String username,
//                                                     AuthorizeModel authorizeModel) throws Exception {
//        if (!username.equals("admin")) {
//            authorizeModel.setUserId(userId);
//        } else {
//            authorizeModel.setUserId(null);
//        }
//        return ok(AuthorizeService.search(authorizeModel));
//    }

    @GetMapping("/detail")
    @ApiOperation(notes = "查看专利委托详情", value = "查看专利委托详情", produces = "application/json")
    public ApiResponse<AuthorizeVo> selectAuthorizeDetail(@RequestParam("authorizeId") Long AuthorizeId) throws Exception {
        return ok(AuthorizeService.selectAuthorizeDetail(AuthorizeId));
    }

    @PutMapping("/updatestate")
    @ApiOperation(notes = "修改委托状态", value = "修改委托状态", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "remoteId", value = "委托id(CNUIP2中的remoteId)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "修改后的状态", paramType = "query", dataType = "String")
    })
    public ApiResponse<String> updateState(@RequestParam String remoteId, @RequestParam String state) throws Exception {
        return ok(AuthorizeService.updateState(remoteId, state));
    }

    @ApiIgnore
    @GetMapping("/base/an")
    @ApiOperation(notes = "已被使用的专利an(内部调用)", value = "已被使用的专利an(内部调用)", produces = "application/json")
    public ApiResponse<List<String>> searchAn(String editorName, String orgName) throws Exception {
        return ok(AuthorizeService.searchAn(editorName,orgName));
    }

    @ApiIgnore
    @PostMapping("/base/")
    @ApiOperation(notes = "专利委托列表", value = "专利委托列表", produces = "application/json")
    public ApiResponse<PageInfo<AuthorizeVo>> searchByPost(@RequestHeader("X-Request-UserId") Long userId,
                                                           @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                           @RequestHeader("X-Request-UserName") String username,
                                                           @RequestBody AuthorizeModel authorizeModel) throws Exception {
        if (!username.equals("admin")) {
            authorizeModel.setUserId(userId);
        } else {
            authorizeModel.setUserId(null);
        }
        return ok(AuthorizeService.search(orgId,authorizeModel));
    }
}

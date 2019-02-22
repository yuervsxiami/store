package com.cnuip.user.rest;


import com.cnuip.base.domain.params.UserParam;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.UrlUtils;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.user.annotation.DealUser;
import com.cnuip.user.service.UserService;
import com.cnuip.user.vo.UserValidateVo;
import com.cnuip.user.vo.UserVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/user")
@Api(value = "UserController", description = "用户接口")
public class UserController extends ControllerResponse {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(notes = "用户登录", value = "用户登录", produces = "application/json")
    public ApiResponse<UserVo> login(@RequestHeader("X-Request-Platform") String platform,
                                     @RequestHeader("X-Request-Ip") String ip,
                                     @RequestBody UserVo user) throws Exception {
        return ok(userService.login(user, platform, ip));
    }

    @PostMapping("/")
    @ApiOperation(notes = "新增用户", value = "新增用户", produces = "application/json")
    @DealUser
    public ApiResponse<User> add(@RequestHeader("X-Request-UserId") Long editorId,
                                 @RequestHeader("X-Request-UserName") String editorName,
                                 @RequestHeader("X-Request-OrganizationId") Long orgId,
                                 @RequestBody UserVo userVo) throws Exception {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String orgName = UrlUtils.decode(servletRequest.getHeader("X-Request-OrganizationName"));
        userVo.setEditorId(editorId);
        userVo.setEditorName(editorName);
        userVo.setOrganizationId(orgId);
        userVo.setOrganizationName(orgName);
        return ok(userService.addUser(userVo));
    }

    @PostMapping("/register")
    @ApiOperation(notes = "用户注册", value = "用户注册", produces = "application/json")
    @DealUser
    public ApiResponse<UserVo> register(@RequestHeader("X-Request-Ip") String ip,
                                        @RequestBody UserVo userVo) throws Exception {
        return ok(userService.register(userVo, ip));
    }

    @DeleteMapping("/")
    public ApiResponse<String> delete(@RequestParam Long userId) throws Exception {
        return ok(userService.deleteUserVo(userId));
    }

    @GetMapping("/")
    public ApiResponse<PageInfo<User>> queryUser(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                 UserParam userParam) throws Exception {
        userParam.setOrganizationId(orgId);
        return ok(userService.selectMany(userParam));
    }

    @GetMapping("/admin")
    public ApiResponse<User> queryAdminUser(@RequestParam Long organizationId) throws Exception {
        UserParam userParam = new UserParam();
        userParam.setOrganizationId(organizationId);
        userParam.setUsername("admin");
        return ok(userService.selectOne(userParam));
    }

    @GetMapping("/department/none")
    @ApiOperation(notes = "注册用户列表(没有归属部门)", value = "注册用户列表(没有归属部门)", produces = "application/json")
    public ApiResponse<PageInfo<User>> queryDepartmentNone(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                           @RequestParam(value = "pageNum", required = false) Integer pageNum,
                                                           @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return ok(userService.queryDepartmentNone(orgId, pageNum, pageSize));
    }


    @PutMapping("/")
    public ApiResponse<User> updateUser(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                        @RequestHeader("X-Request-UserId") Long userId,
                                        @RequestHeader(value = "X-Request-OrganizationName",required = false) String orgName,
                                        @RequestHeader(value = "X-Request-UserName",required = false) String userName,
                                        @RequestBody User user) throws Exception {
        user.setOrganizationId(orgId);
        if(orgName!=null){
            user.setOrganizationName(orgName);
        }
        //用于app修改个人信息
        if(userName!=null && !"admin".equals(userName)){
            user.setUsername(userName);
        }
        //检查用户默认值
        String checkValue = user.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        return ok(userService.updateUser(userId,user));
    }

    @PutMapping("/updateForapp")
    public ApiResponse<User> updateUserForapp(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                        @RequestHeader("X-Request-UserId") Long userId,
                                        @RequestHeader(value = "X-Request-OrganizationName",required = false) String orgName,
                                        @RequestHeader(value = "X-Request-UserName",required = false) String userName,
                                        @RequestBody User user) throws Exception {
        user.setOrganizationId(orgId);
        if(orgName!=null){
            user.setOrganizationName(URLDecoder.decode(orgName,"UTF-8"));
        }
        if(userName!=null){
            user.setUsername(userName);
        }

        //检查用户默认值
        String checkValue = user.checkValueForApp();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        return ok(userService.updateUser(userId,user));
    }



    @GetMapping("/detail")
    public ApiResponse<UserVo> queryDetail(@RequestParam Long id) {
        return ok(userService.queryDetail(userService.selectOneByKey(id)));
    }

    @GetMapping("/base/dept")
    public ApiResponse<PageInfo<User>> queryForBase(@RequestParam Long deptId, @RequestParam Long powersId) throws Exception {
        UserParam userParam = new UserParam();
        userParam.setDepartmentId(deptId);
        userParam.setPowersId(powersId);
        return ok(userService.selectMany(userParam));
    }

    @PutMapping("/forgetpwd")
    @ApiOperation(notes = "重置密码", value = "重置密码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "UserValidateVo", name = "userValidateVo", value = "用户验证及密码重置", required = true)
    })
    public ApiResponse<Integer> forgetPassword(@RequestBody UserValidateVo userValidateVo) throws Exception {
        return ok(userService.resetPassword(userValidateVo));
    }

    @ApiIgnore
    @PostMapping("/base/dept/usercount")
    public ApiResponse<PageInfo<User>> queryUserBase(@RequestBody UserParam userParam) throws Exception {
        return ok(userService.selectMany(userParam));
    }

    @GetMapping("/base/dept/examine")
    public ApiResponse<List<Long>> queryDeptIdBase(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                   @RequestParam("powersId") Long powersId){
        return ok(userService.queryDeptIdBase(orgId,powersId));
    }

    @PutMapping("/password")
    @ApiOperation(notes = "用户修改密码", value = "用户修改密码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "X-Request-UserId", value = "用户id", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "passwordOld", value = "旧密码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "passwordNew", value = "新密码", required = true)
    })
    public ApiResponse<Integer> pwdChange(@RequestHeader("X-Request-UserId") String userId,
                                          @RequestParam String passwordOld,
                                          @RequestParam String passwordNew) throws Exception {
        return ok(userService.pwdChange(userId, passwordOld, passwordNew));
    }
}

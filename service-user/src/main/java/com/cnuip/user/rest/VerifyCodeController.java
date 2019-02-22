package com.cnuip.user.rest;

import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.user.rest.exceptions.CustomException;
import com.cnuip.user.service.VerifyCodeService;
import com.cnuip.user.vo.VerifyCodeActionEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhibin on 2018/4/16.
 */
@RestController
@RequestMapping(value = "/v1/verifycode")
@Api(value = "VerifyCodeController", description = "用户验证码接口")
public class VerifyCodeController extends ControllerResponse {
    @Autowired
    private VerifyCodeService verifyCodeService;

    @GetMapping("/")
    @ApiOperation(notes = "获取验证码", value = "获取验证码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "phone", value = "手机号码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "action", value = "动作(LOGIN.登录 REGISTER.注册 FORGET.重置)", required = true)
    })
    public ApiResponse<Boolean> getVerifyCode(String phone, VerifyCodeActionEnum action) throws CustomException {
        return ok(verifyCodeService.sendVerifyCode(phone,action));
    }

    @GetMapping("/check")
    @ApiOperation(notes = "校验验证码", value = "校验验证码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "phone", value = "手机号码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "verifyCode", value = "验证码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "action", value = "动作(LOGIN.登录 REGISTER.注册 RESET.重置)", required = true)
    })
    public ApiResponse<Boolean> checkVerifyCode(String phone, String verifyCode, String action) {
        return ok(verifyCodeService.checkVerifyCode(phone, verifyCode, VerifyCodeActionEnum.valueOf(action)));
    }
}

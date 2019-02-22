package com.cnuip.user.rest;

import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.user.service.AliSmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhibin on 2018/4/17.
 */
@RestController
@Api(value = "AliSmsController", description = "阿里短信接口")
public class AliSmsController extends ControllerResponse {

    @Autowired
    private AliSmsService aliSmsService;

    @GetMapping("/v1/ali/message")
    @ApiOperation(notes = "发送短信", value = "发送短信", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "phone", value = "手机号码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "templateCode", value = "短信模板 code", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "content", value = "模板中的变量替换JSON串", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "param", value = "变量值", required = true)
    })
    public ApiResponse<Boolean> send(String phone, String templateCode, String content, String param) throws Exception {
        return ok(aliSmsService.sendSms(phone, templateCode, content, param));
    }
}

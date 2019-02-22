package com.cnuip.user.rest;

import com.cnuip.base.domain.params.AuthorityParam;
import com.cnuip.base.domain.user.Authority;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.user.service.AuthorityService;
import com.cnuip.user.vo.AuthorityVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/authority")
@Api(value = "AuthController", description = "权限接口")
public class AuthorityController extends ControllerResponse {

    @Autowired
    private AuthorityService authorityService;


    @PostMapping("/")
    public ApiResponse<Authority> addAuth(@RequestHeader("X-Request-UserId") Long editorId,
                                          @RequestHeader("X-Request-UserName") String editorName,
                                          @RequestBody Authority authority) throws Exception {
        authority.setEditorId(editorId);
        authority.setEditorName(editorName);
        return ok(authorityService.insert(authorityService.check(authority)));
    }

    @DeleteMapping("/")
    public ApiResponse<String> deleteAuth(@RequestParam Long id) throws Exception {
        authorityService.deleteByKey(id);
        return ok(null);
    }

    @GetMapping("/")
    public ApiResponse<List<AuthorityVo>> queryAuth(AuthorityParam authorityParam) throws Exception {
        return ok(authorityService.tree(authorityParam));
    }

    @PutMapping("/")
    public ApiResponse<Authority> updateRole(@RequestBody Authority authority) throws Exception {
        //检查权限默认值
        String checkValue = authority.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        return ok(authorityService.updateByKey(authority.getId(), authority));
    }

    @ApiIgnore
    @PostMapping("/base")
    public ApiResponse<List<AuthorityVo>> queryAuthBase(@RequestBody AuthorityParam authorityParam) throws Exception {
        return ok(authorityService.tree(authorityParam));
    }
}

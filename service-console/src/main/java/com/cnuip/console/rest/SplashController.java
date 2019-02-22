package com.cnuip.console.rest;

import com.cnuip.base.domain.console.Splash;
import com.cnuip.base.domain.params.SplashParam;
import com.cnuip.console.service.SplashService;
import com.cnuip.console.vo.SplashVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/splash")
@Api(value = "SplashController", description = "开屏接口")
public class SplashController extends ControllerResponse {
    @Autowired
    private SplashService splashService;

    @PostMapping("/")
    @ApiOperation(notes = "新增开屏", value = "新增开屏", produces = "application/json")
    public ApiResponse<Splash> add(@RequestHeader("X-Request-UserId") Long editorId,
                                   @RequestHeader("X-Request-UserName") String editorName,
                                   @RequestBody Splash splash) throws Exception {
        splash.setEditorId(editorId);
        splash.setEditorName(editorName);
        return ok(splashService.insertSplash(splash));
    }

    @PutMapping("/")
    @ApiOperation(notes = "修改开屏", value = "修改开屏", produces = "application/json")
    public ApiResponse<Splash> update(@RequestBody Splash splash) throws Exception {
        return ok(splashService.updateSplash(splash));
    }

    @GetMapping("/")
    @ApiOperation(notes = "开屏列表", value = "开屏列表", produces = "application/json")
    public ApiResponse<PageInfo<SplashVo>> list(SplashParam param) {
        return ok(splashService.list(param));
    }

    @GetMapping("/current")
    @ApiOperation(notes = "当前开屏", value = "当前开屏", produces = "application/json")
    public ApiResponse<Splash> current() {
        return ok(splashService.current());
    }
}

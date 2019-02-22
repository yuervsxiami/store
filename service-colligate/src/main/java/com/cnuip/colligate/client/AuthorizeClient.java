package com.cnuip.colligate.client;


import com.cnuip.colligate.vo.AuthorizeModel;
import com.cnuip.colligate.vo.AuthorizeVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "store2-service-authorize")
public interface AuthorizeClient {

    @PostMapping("/v1/authorize/")
    ApiResponse<AuthorizeVo> add(@RequestBody AuthorizeVo authorizeVo) throws Exception;

    @GetMapping("/v1/authorize/base/an")
    ApiResponse<List<String>> searchAn(@RequestParam(value = "editorName") String editorName, @RequestParam(value = "orgName") String orgName) throws Exception;

    @PostMapping("/v1/authorize/base/")
    @ApiOperation(notes = "专利委托列表", value = "专利委托列表", produces = "application/json")
    ApiResponse<PageInfo<AuthorizeVo>> search(@RequestHeader("X-Request-UserId") Long userId,
                                              @RequestHeader("X-Request-OrganizationId") Long orgId,
                                              @RequestHeader("X-Request-UserName") String username,
                                              @RequestBody AuthorizeModel authorizeModel) throws Exception;
}

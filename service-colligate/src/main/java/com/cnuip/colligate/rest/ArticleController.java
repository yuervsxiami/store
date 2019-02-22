package com.cnuip.colligate.rest;


import com.cnuip.colligate.service.ArticleService;
import com.cnuip.colligate.vo.ArticleQueryParam;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/colligate/article")
@Api(value = "ArticleController", description = "文章接口")
public class ArticleController extends ControllerResponse {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    @ApiOperation(notes = "查询文章列表", value = "查询文章列表", produces = "application/json")
    ApiResponse searchList(ArticleQueryParam articleQueryParam) throws Exception {
        return ok(articleService.list(articleQueryParam));
    }
}

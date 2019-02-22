package com.cnuip.patent.rest;


import com.cnuip.base.domain.params.FavoriteParam;
import com.cnuip.base.domain.patent.Favorite;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.patent.service.FavoriteService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Crixalis on 2018/5/4.
 */
@RestController
@RequestMapping(value = "/v1/favorite")
@Api(value = "FavoriteController", description = "专利收藏接口")
public class FavoriteController extends ControllerResponse {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/")
    @ApiOperation(notes = "加入收藏", value = "加入收藏", produces = "application/json;charset=UTF-8")
    public ApiResponse<Favorite> add(@RequestBody Favorite favorite) throws Exception {
        favoriteService.check(favorite);
        return ok(favoriteService.addFavorite(favorite));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "取消收藏", value = "取消收藏", produces = "application/json")
    public ApiResponse<Favorite> delete(@RequestParam Long favoriteId) throws Exception {
        Favorite favorite = favoriteService.selectOneByKey(favoriteId);
        favoriteService.deleteByKey(favoriteId);
        return ok(favorite);
    }

    @GetMapping("/")
    @ApiOperation(notes = "收藏列表", value = "收藏列表", produces = "application/json")
    public ApiResponse<PageInfo<Favorite>> search(@RequestHeader("X-Request-UserId") Long userId,
                                                  FavoriteParam favoriteParam) throws Exception {
        favoriteParam.setUserId(userId);
        if(StringUtils.isEmpty(favoriteParam.getOrderBy())){
            favoriteParam.setOrderBy(" id desc");
        }
        return ok(favoriteService.selectMany(favoriteParam));
    }
}

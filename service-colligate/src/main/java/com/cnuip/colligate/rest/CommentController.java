package com.cnuip.colligate.rest;

import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.service.CommentService;
import com.cnuip.colligate.vo.RequirementCountVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/colligate/comment")
@Api(value = "CommentController", description = "咨询接口")
public class CommentController extends ControllerResponse {

    @Autowired
    private CommentService commentService;

    @GetMapping("/shop")
    @ApiOperation(notes = "获取店铺咨询列表", value = "获取店铺咨询列表", produces = "application/json")
    public ApiResponse getShopComment(@RequestHeader("X-Request-UserId") Long userId,
                                      @RequestParam(required = false) String isReply,
                                      @RequestParam int pageSize,
                                      @RequestParam int pageNum) throws ClientException {
        return ok(commentService.shopList(userId, isReply, pageSize, pageNum));
    }

    @PutMapping("/shop/reply")
    @ApiOperation(notes = "回复店铺咨询", value = "回复店铺咨询", produces = "application/json")
    public ApiResponse reply(Long shopCommentId, String replyContent) throws ClientException {
        return ok(commentService.reply(shopCommentId, replyContent));
    }


    @GetMapping("/goods")
    @ApiOperation(notes = "获取商品咨询列表", value = "获取商品咨询列表", produces = "application/json")
    public ApiResponse getGoodsComment(@RequestHeader("X-Request-UserId") Long userId,
                                       @RequestHeader("X-Request-UserName") String username,
                                       @RequestHeader("X-Request-OrganizationId") Long orgId,
                                       @RequestParam(required = false) String isReply,
                                       @RequestParam int pageSize,
                                       @RequestParam int pageNum) throws ClientException {
        return ok(commentService.goodsList(userId, username,orgId,isReply, pageSize, pageNum));
    }


    @PutMapping("/goods/reply")
    @ApiOperation(notes = "获取商品咨询列表", value = "获取商品咨询列表", produces = "application/json")
    public ApiResponse replyComment(@RequestHeader("X-Request-UserId") String userId, Long goodsCommentId, String replyContent) throws ClientException {
        return ok(commentService.replyGoods(userId, goodsCommentId,replyContent));
    }

    @GetMapping("/count")
    @ApiOperation(notes = "需求及留言统计", value = "需求及留言统计", produces = "application/json")
    public ApiResponse<RequirementCountVo> findCommentCount(@RequestHeader("X-Request-UserId") Long userId) throws Exception{
        return ok(commentService.findCommentCount(userId));
    }
}

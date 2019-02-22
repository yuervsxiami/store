package com.cnuip.colligate.service;

import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.vo.RequirementCountVo;
import com.cnuip.gaea.service.web.ApiResponse;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface CommentService {

    /**
     * 店铺咨询列表
     * @param userId
     * @param isReply
     * @param pageSize
     * @param pageNum
     * @return
     */
    ApiResponse shopList(Long userId, String isReply, int pageSize, int pageNum) throws ClientException;

    /**
     * 回复店铺咨询
     * @param shopCommentId
     * @param replyContent
     * @return
     * @throws ClientException
     */
    ApiResponse reply(Long shopCommentId, String replyContent) throws ClientException;

    /**
     * 商品咨询列表
     * @param userId
     * @param orgId
     * @param isReply
     * @param pageSize
     * @param pageNum
     * @return
     */
    ApiResponse goodsList(Long userId, String username, Long orgId, String isReply, int pageSize, int pageNum) throws ClientException;

    /**
     * 回复商品留言
     * @param userId
     * @param goodsCommentId
     * @param replyContent
     * @return
     * @throws ClientException
     */
    ApiResponse replyGoods(String userId, Long goodsCommentId, String replyContent) throws ClientException;

    RequirementCountVo findCommentCount(Long userId) throws ClientException;
}

package com.cnuip.console.service;

import com.cnuip.base.domain.console.Post;
import com.cnuip.base.domain.params.PostParam;
import com.cnuip.base.service.AbstractService;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface PostService extends AbstractService<Post, PostParam> {

    /**
     * 新增岗位
     * @param post
     * @return
     * @throws Exception
     */
    Post addPost(Post post) throws Exception;

    /**
     * 修改岗位
     * @param post
     * @return
     */
    Post updatePost(Post post) throws Exception;

    Post deletePost(Long id) throws Exception;
}

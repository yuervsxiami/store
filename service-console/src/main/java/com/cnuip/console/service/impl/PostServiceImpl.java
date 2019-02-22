package com.cnuip.console.service.impl;

import com.cnuip.base.domain.console.Post;
import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.PostParam;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.console.repository.PostMapper;
import com.cnuip.console.repository.base.PostBaseMapper;
import com.cnuip.console.rest.exceptions.CustomException;
import com.cnuip.console.rest.exceptions.ResponseEnum;
import com.cnuip.console.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class PostServiceImpl extends AbstractServiceImpl<Post, PostParam> implements PostService {

    @Autowired
    private PostBaseMapper postBaseMapper;

    @Autowired
    private PostMapper postMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post addPost(Post post) throws Exception{
        this.checkPost(post);
        this.check(post);
        postBaseMapper.insert(post);
        return postBaseMapper.selectOneByKey(post.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post updatePost(Post post) throws Exception{
        //检查岗位默认值
        String checkValue = post.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        this.checkPost(post);
        postBaseMapper.updateByKey(post.getId(),post);
        return postBaseMapper.selectOneByKey(post.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post deletePost(Long id) throws Exception {
        PostParam postParam = new PostParam();
        postParam.setIsDelete(YesNoEnum.NO.toString());
        postParam.setParentId(id);
        int count = this.count(postParam);
        if (count != 0) {
            throw new CustomException("无法删除!存在子岗位");
        }
        return this.updateFieldByKey(id, "isDelete", YesNoEnum.YES);
    }

    private void checkPost(Post post) throws Exception{
        PostParam postParam = new PostParam();
        postParam.setOrganizationId(post.getOrganizationId());
        postParam.setDepartmentId(post.getDepartmentId());
        postParam.setName(post.getName());
        Post newPost = postBaseMapper.selectOne(postParam);
        if(newPost != null){
            throw new CustomException(ResponseEnum.POST_EXIST_ERROR);
        }
    }
}
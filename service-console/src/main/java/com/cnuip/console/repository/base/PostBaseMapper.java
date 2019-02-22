package com.cnuip.console.repository.base;

import com.cnuip.base.domain.console.Post;
import com.cnuip.base.domain.params.PostParam;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface PostBaseMapper extends AbstractMapper<Post, PostParam>
{}
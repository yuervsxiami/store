package com.cnuip.authorize.repository.base;


import com.cnuip.base.domain.authorize.Authorize;
import com.cnuip.base.domain.params.AuthorizeParam;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface AuthorizeBaseMapper extends AbstractMapper<Authorize, AuthorizeParam>
{}
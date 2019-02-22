package com.cnuip.authorize.repository.base;


import com.cnuip.base.domain.authorize.AuthorizePatent;
import com.cnuip.base.domain.params.AuthorizePatentParam;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface AuthorizePatentBaseMapper extends AbstractMapper<AuthorizePatent, AuthorizePatentParam>
{}
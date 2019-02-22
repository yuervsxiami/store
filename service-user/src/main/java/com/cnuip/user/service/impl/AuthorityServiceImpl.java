package com.cnuip.user.service.impl;

import com.cnuip.base.domain.params.AuthorityParam;
import com.cnuip.base.domain.user.Authority;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.utils.TreeUtils;
import com.cnuip.user.repository.AuthorityMapper;
import com.cnuip.user.repository.base.AuthorityBaseMapper;
import com.cnuip.user.service.AuthorityService;
import com.cnuip.user.vo.AuthorityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 王志斌
 * @Date: 2018/4/3 15:27
 */
@Service
public class AuthorityServiceImpl
        extends AbstractServiceImpl<Authority, AuthorityParam>
        implements AuthorityService {

    @Autowired
    private AuthorityBaseMapper authorityBaseMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public List<AuthorityVo> tree(AuthorityParam authorityParam) {
        List<Authority> all = authorityBaseMapper.getAll(authorityParam);
        List<AuthorityVo> authorityVos = new ArrayList<>();
        for (Authority e : all) {
            AuthorityVo authorityVo = new AuthorityVo();
            BeanUtils.copyProperties(e, authorityVo);
            authorityVos.add(authorityVo);
        }
        return TreeUtils.listToTree(authorityVos);
    }
}
package com.cnuip.process.service.impl;


import com.cnuip.base.domain.params.TmplProcessPersonParam;
import com.cnuip.base.domain.process.TmplProcessPerson;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.process.repository.TmplProcessPersonMapper;
import com.cnuip.process.repository.base.TmplProcessPersonBaseMapper;
import com.cnuip.process.service.TmplProcessPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class TmplProcessPersonServiceImpl
        extends AbstractServiceImpl<TmplProcessPerson, TmplProcessPersonParam>
        implements TmplProcessPersonService {

    @Autowired
    private TmplProcessPersonBaseMapper tmplProcessPersonBaseMapper;

    @Autowired
    private TmplProcessPersonMapper tmplProcessPersonMapper;
}
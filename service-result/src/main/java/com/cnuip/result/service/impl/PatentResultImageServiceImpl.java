package com.cnuip.result.service.impl;

import com.cnuip.base.domain.params.PatentResultImageParam;
import com.cnuip.base.domain.result.PatentResultImage;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.result.repository.base.PatentResultImageBaseMapper;
import com.cnuip.result.service.PatentResultImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xjt on 2018/8/30.
 */
@Service
public class PatentResultImageServiceImpl extends AbstractServiceImpl<PatentResultImage, PatentResultImageParam> implements PatentResultImageService {

    @Autowired
    private PatentResultImageBaseMapper patentResultImageBaseMapper;
}

package com.cnuip.result.service.impl;

import com.cnuip.base.domain.params.LabelValueParam;
import com.cnuip.base.domain.result.LabelValue;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.result.repository.base.LabelValueBaseMapper;
import com.cnuip.result.service.LabelValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xjt on 2018/8/30.
 */
@Service
public class LabelValueServiceImpl extends AbstractServiceImpl<LabelValue, LabelValueParam> implements LabelValueService {

    @Autowired
    private LabelValueBaseMapper labelValueBaseMapper;
}

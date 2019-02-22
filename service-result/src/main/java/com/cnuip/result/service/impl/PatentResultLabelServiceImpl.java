package com.cnuip.result.service.impl;

import com.cnuip.base.domain.params.PatentResultLabelParam;
import com.cnuip.base.domain.result.PatentResultLabel;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.result.repository.base.PatentResultLabelBaseMapper;
import com.cnuip.result.service.PatentResultLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xjt on 2018/8/30.
 */
@Service
public class PatentResultLabelServiceImpl extends AbstractServiceImpl<PatentResultLabel, PatentResultLabelParam> implements PatentResultLabelService {

    @Autowired
    private PatentResultLabelBaseMapper patentResultLabelBaseMapper;
}

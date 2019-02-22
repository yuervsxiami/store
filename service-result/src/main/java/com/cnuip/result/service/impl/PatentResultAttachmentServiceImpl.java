package com.cnuip.result.service.impl;

import com.cnuip.base.domain.params.PatentResultAttachmentParam;
import com.cnuip.base.domain.result.PatentResultAttachment;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.result.repository.base.PatentResultAttachmentBaseMapper;
import com.cnuip.result.service.PatentResultAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xjt on 2018/8/30.
 */
@Service
public class PatentResultAttachmentServiceImpl extends AbstractServiceImpl<PatentResultAttachment, PatentResultAttachmentParam> implements PatentResultAttachmentService {

    @Autowired
    private PatentResultAttachmentBaseMapper patentResultAttachmentBaseMapper;
}

package com.cnuip.process.service.impl;

import com.cnuip.base.domain.params.ProcessAttachmentParam;
import com.cnuip.base.domain.process.ProcessAttachment;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.process.repository.ProcessAttachmentMapper;
import com.cnuip.process.repository.base.ProcessAttachmentBaseMapper;
import com.cnuip.process.service.ProcessAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class ProcessAttachmentServiceImpl
        extends AbstractServiceImpl<ProcessAttachment, ProcessAttachmentParam>
        implements ProcessAttachmentService {

    @Autowired
    private ProcessAttachmentBaseMapper processAttachmentBaseMapper;

    @Autowired
    private ProcessAttachmentMapper processAttachmentMapper;
}
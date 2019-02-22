package com.cnuip.process.service.impl;


import com.cnuip.base.domain.params.ProcessRequisitionParam;
import com.cnuip.base.domain.process.ProcessRequisition;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.process.repository.ProcessRequisitionMapper;
import com.cnuip.process.repository.base.ProcessRequisitionBaseMapper;
import com.cnuip.process.service.ProcessRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class ProcessRequisitionServiceImpl extends AbstractServiceImpl<ProcessRequisition, ProcessRequisitionParam> implements ProcessRequisitionService {

    @Autowired
    private ProcessRequisitionBaseMapper processRequisitionBaseMapper;

    @Autowired
    private ProcessRequisitionMapper processRequisitionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProcessRequisition> addProcessRequisition(List<ProcessRequisition> processRequisitionList) throws Exception {
        for(ProcessRequisition processRequisition:processRequisitionList){
            this.check(processRequisition);
            processRequisitionBaseMapper.insert(processRequisition);
        }
        return processRequisitionList;
    }
}
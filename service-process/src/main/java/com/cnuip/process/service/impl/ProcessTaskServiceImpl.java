package com.cnuip.process.service.impl;

import com.cnuip.base.domain.params.ProcessTaskParam;
import com.cnuip.base.domain.process.ProcessTask;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.process.repository.ProcessTaskMapper;
import com.cnuip.process.repository.base.ProcessTaskBaseMapper;
import com.cnuip.process.service.ProcessTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class ProcessTaskServiceImpl extends AbstractServiceImpl<ProcessTask, ProcessTaskParam> implements ProcessTaskService {

    @Autowired
    private ProcessTaskBaseMapper processTaskBaseMapper;

    @Autowired
    private ProcessTaskMapper processTaskMapper;


    @Override
    public ProcessTask queryProcessTask(Long processId, Long tmplProcessTaskId) throws Exception{
        ProcessTaskParam processTaskParam = new ProcessTaskParam();
        processTaskParam.setProcessId(processId);
        processTaskParam.setTmplProcessTaskId(tmplProcessTaskId);
        return processTaskBaseMapper.selectOne(processTaskParam);
    }
}
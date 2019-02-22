package com.cnuip.process.service;


import com.cnuip.base.domain.params.ProcessTaskParam;
import com.cnuip.base.domain.process.ProcessTask;
import com.cnuip.base.service.AbstractService;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface ProcessTaskService extends AbstractService<ProcessTask, ProcessTaskParam> {
    /**
     * 根据提案ID和流程环节ID查询提案环节
     * @param processId
     * @param tmplProcessTaskId
     * @return
     */
    ProcessTask queryProcessTask(Long processId, Long tmplProcessTaskId) throws Exception;
}

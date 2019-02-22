package com.cnuip.process.service;


import com.cnuip.base.domain.params.ProcessTaskUserParam;
import com.cnuip.base.domain.process.ProcessTaskUser;
import com.cnuip.base.service.AbstractService;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface ProcessTaskUserService extends AbstractService<ProcessTaskUser, ProcessTaskUserParam> {

    /**
     * 修改提案环节状态
     * @param processTaskUser
     * @return
     */
    ProcessTaskUser updateProcessTaskState(ProcessTaskUser processTaskUser) throws Exception;
}

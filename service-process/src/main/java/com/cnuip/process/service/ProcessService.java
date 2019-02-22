package com.cnuip.process.service;


import com.cnuip.base.domain.params.ProcessParam;
import com.cnuip.base.domain.process.Process;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.service.AbstractService;
import com.cnuip.process.vo.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface ProcessService extends AbstractService<Process, ProcessParam> {

    /**
     * 新增提案
     * @param processVo
     * @return
     */
    ProcessVo addProcess(ProcessVo processVo) throws Exception;

    ProcessVo selectProcessDetail(Long processId) throws Exception;

    ProcessVo deleteProcess(Long processId) throws Exception;

    PageInfo<ProcessListVo> selectProcess(Long userId, String username, ProcessParam processParam) throws Exception;

    PageInfo<Process> selectEditorProcess(Long userId, String username, ProcessParam processParam) throws Exception;

    List<User> selectProcessUser(Long orgId) throws Exception;

    PageInfo<Process> selectCopyProcess(Long userId, ProcessParam processParam) throws Exception;

    ProcessNumVo searchProcessNum(Long userId, Long orgId) throws Exception ;

    ProcessAppVo selectAppProcess(Long userId, String username, ProcessParam processParam) throws Exception;

    ProcessAppAuditVo searchAppAudit(Long userId, String username, ProcessParam processParam) throws Exception;

    ProcessAppVo searchAppCopy(Long userId, ProcessParam processParam) throws Exception;

    String getProcessNum()throws Exception;
}

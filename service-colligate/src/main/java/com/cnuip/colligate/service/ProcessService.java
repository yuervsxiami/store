package com.cnuip.colligate.service;

import com.cnuip.colligate.vo.ProcessVo;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface ProcessService{

    /**
     * 新增提案
     * @param processVo
     * @return
     */
    ProcessVo addProcess(Long editorId, Long orgId, ProcessVo processVo) throws Exception;
}

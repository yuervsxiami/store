package com.cnuip.process.repository;

import com.cnuip.base.domain.process.ProcessTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface ProcessTaskMapper
{
    ProcessTask selectNextTask(@Param("processId") Long processId, @Param("processTaskId") Long processTaskId);
}
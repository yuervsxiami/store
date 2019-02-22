package com.cnuip.process.repository;

import com.cnuip.base.domain.params.ProcessParam;
import com.cnuip.base.domain.process.Process;
import com.cnuip.base.domain.user.User;
import com.cnuip.process.vo.ProcessListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface ProcessMapper
{
    List<ProcessListVo> selectProcessForEditor(@Param("params") ProcessParam processParam, @Param("userId") Long userId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    List<User> selectProcessUser(@Param("orgId") Long orgId);

    List<Process> selectCopyProcess(@Param("params") ProcessParam processParam, @Param("userId") Long userId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
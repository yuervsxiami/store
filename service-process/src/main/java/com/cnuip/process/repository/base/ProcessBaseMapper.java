package com.cnuip.process.repository.base;

import com.cnuip.base.domain.params.ProcessParam;
import com.cnuip.base.domain.process.Process;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface ProcessBaseMapper extends AbstractMapper<Process, ProcessParam>
{}
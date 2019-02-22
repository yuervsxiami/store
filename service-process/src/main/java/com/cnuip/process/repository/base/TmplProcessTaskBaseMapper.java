package com.cnuip.process.repository.base;

import com.cnuip.base.domain.params.TmplProcessTaskParam;
import com.cnuip.base.domain.process.TmplProcessTask;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface TmplProcessTaskBaseMapper extends AbstractMapper<TmplProcessTask, TmplProcessTaskParam>
{}
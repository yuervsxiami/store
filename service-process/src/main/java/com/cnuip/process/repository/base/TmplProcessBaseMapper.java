package com.cnuip.process.repository.base;

import com.cnuip.base.domain.params.TmplProcessParam;
import com.cnuip.base.domain.process.TmplProcess;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface TmplProcessBaseMapper extends AbstractMapper<TmplProcess, TmplProcessParam>
{}
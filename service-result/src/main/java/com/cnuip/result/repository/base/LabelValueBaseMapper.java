package com.cnuip.result.repository.base;

import com.cnuip.base.domain.params.LabelValueParam;
import com.cnuip.base.domain.result.LabelValue;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface LabelValueBaseMapper extends AbstractMapper<LabelValue, LabelValueParam>
{}
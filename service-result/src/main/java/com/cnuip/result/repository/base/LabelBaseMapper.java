package com.cnuip.result.repository.base;

import com.cnuip.base.domain.params.LabelParam;
import com.cnuip.base.domain.result.Label;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface LabelBaseMapper extends AbstractMapper<Label, LabelParam>
{}
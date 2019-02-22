package com.cnuip.result.service;

import com.cnuip.base.domain.params.LabelParam;
import com.cnuip.base.domain.result.Label;
import com.cnuip.base.service.AbstractService;
import com.cnuip.result.vo.LabelVo;
import com.github.pagehelper.PageInfo;

/**
 * Created by xjt on 2018/8/30.
 */
public interface LabelService extends AbstractService<Label, LabelParam> {

    /**
     * 新增成果标签
     * @param label
     * @return
     */
    Label addLabel(Label label) throws Exception;

    /**
     * 查询成果标签列表
     * @param labelParam
     * @return
     */
    PageInfo<LabelVo> selectLabel(LabelParam labelParam) throws Exception;

    /**
     * 删除成果标签
     * @param labelId
     * @return
     */
    LabelVo deleteLabel(long labelId) throws Exception;
}

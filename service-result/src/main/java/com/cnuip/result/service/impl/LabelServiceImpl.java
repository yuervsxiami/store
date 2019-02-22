package com.cnuip.result.service.impl;

import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.LabelParam;
import com.cnuip.base.domain.params.LabelValueParam;
import com.cnuip.base.domain.result.Label;
import com.cnuip.base.domain.result.LabelValue;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.result.repository.base.LabelBaseMapper;
import com.cnuip.result.repository.base.LabelValueBaseMapper;
import com.cnuip.result.service.LabelService;
import com.cnuip.result.service.LabelValueService;
import com.cnuip.result.vo.LabelVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjt on 2018/8/30.
 */
@Service
public class LabelServiceImpl extends AbstractServiceImpl<Label, LabelParam> implements LabelService {

    @Autowired
    private LabelBaseMapper labelBaseMapper;

    @Autowired
    private LabelValueBaseMapper labelValueBaseMapper;

    @Autowired
    private LabelValueService labelValueService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Label addLabel(Label label) throws Exception{
        //新增成果标签
        this.check(label);
        labelBaseMapper.insert(label);
        return label;
    }

    @Override
    public PageInfo<LabelVo> selectLabel(LabelParam labelParam) throws Exception{
        PageHelper.startPage(labelParam.getPageNum()==null?1:labelParam.getPageNum(), labelParam.getPageSize()==null?10:labelParam.getPageSize());
        //查询成果标签
        List<Label> labelList = labelBaseMapper.getAll(labelParam);
        //查询成果标签值
        List<LabelVo> labelVoList = new ArrayList<>();
        if(labelList != null && labelList.size() > 0){
            for(Label label:labelList){
                LabelValueParam labelValueParam = new LabelValueParam();
                labelValueParam.setLabelId(label.getId());
                labelValueParam.setIsDelete(YesNoEnum.NO.name());
                List<LabelValue> labelValueList = labelValueBaseMapper.getAll(labelValueParam);
                LabelVo labelVo = new LabelVo();
                BeanUtils.copyProperties(label,labelVo);
                labelVo.setLabelValueList(labelValueList);
                labelVoList.add(labelVo);
            }
        }

        return new PageInfo<>(labelVoList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LabelVo deleteLabel(long labelId) throws Exception{
        //删除成果标签
        labelBaseMapper.updateFieldByKey(labelId,"isDelete", YesNoEnum.YES);
        //删除成果标签值
        LabelValueParam labelValueParam = new LabelValueParam();
        labelValueParam.setLabelId(labelId);
        LabelValue labelValue = new LabelValue();
        labelValue.setIsDelete(YesNoEnum.YES.name());
        labelValueBaseMapper.update(labelValueParam,labelValue);
        return null;
    }
}

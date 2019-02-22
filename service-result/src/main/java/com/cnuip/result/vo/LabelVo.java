package com.cnuip.result.vo;

import com.cnuip.base.domain.result.Label;
import com.cnuip.base.domain.result.LabelValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by xjt on 2018/8/22.
 */
@Getter
@Setter
@ApiModel(value = "成果标签VO", description = "成果标签VO,包含成果标签值", parent = Label.class)
public class LabelVo extends Label {

    /** 成果标签值集合 */
    @ApiModelProperty(value="成果标签值集合", name="labelValueList", dataType="ArrayList")
    private List<LabelValue> labelValueList;

}

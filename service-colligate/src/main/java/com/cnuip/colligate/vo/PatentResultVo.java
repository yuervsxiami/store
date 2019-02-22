package com.cnuip.colligate.vo;

import com.cnuip.base.domain.result.LabelValue;
import com.cnuip.base.domain.result.PatentResult;
import com.cnuip.base.domain.result.PatentResultAttachment;
import com.cnuip.base.domain.result.PatentResultImage;
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
@ApiModel(value = "专利成果VO", description = "专利成果VO,包含成果附件及标签列表", parent = PatentResult.class)
public class PatentResultVo extends PatentResult {

    /** 专利成果附件集合 */
    @ApiModelProperty(value="专利成果附件集合", name="patentResultAttachmentList", dataType="ArrayList")
    private List<PatentResultAttachment> patentResultAttachmentList;

    /** 专利成果图片集合 */
    @ApiModelProperty(value="专利成果图片集合", name="patentResultImageList", dataType="ArrayList")
    private List<PatentResultImage> patentResultImageList;

    /** 专利成果标签集合 */
    @ApiModelProperty(value="专利成果标签值集合", name="labelValueList", dataType="ArrayList")
    private List<LabelValue> labelValueList;
}

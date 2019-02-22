package com.cnuip.result.vo;

import com.cnuip.base.domain.result.PatentResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by xjt on 2018/8/22.
 */
@Getter
@Setter
@ApiModel(value = "专利成果列表VO", description = "专利成果列表VO,包含统计数据")
public class ResultAppVo {

    @ApiModelProperty(value="提案列表", name="patentResultList", dataType="Long")
    private PageInfo<PatentResult> patentResultList;

    @ApiModelProperty(value="通过小试数", name="smallNum", dataType="Long")
    private Long smallNum;

    @ApiModelProperty(value="通过中试数", name="pilotNum", dataType="Long")
    private Long pilotNum;

    @ApiModelProperty(value="已有样品数", name="copyNum", dataType="Long")
    private Long sampleNum;
}

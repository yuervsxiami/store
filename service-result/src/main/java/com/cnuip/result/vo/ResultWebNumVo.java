package com.cnuip.result.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by xjt on 2018/8/22.
 */
@Getter
@Setter
@ApiModel(value = "专利成果统计数据VO", description = "专利成果统计数据VO")
public class ResultWebNumVo {

    @ApiModelProperty(value="成果总量", name="totalNum", dataType="Long")
    private Long totalNum;

    @ApiModelProperty(value="通过小试数", name="smallNum", dataType="Long")
    private Long smallNum;

    @ApiModelProperty(value="通过中试数", name="pilotNum", dataType="Long")
    private Long pilotNum;

    @ApiModelProperty(value="已有样品数", name="copyNum", dataType="Long")
    private Long sampleNum;

    @ApiModelProperty(value="可以量产数", name="batchNum", dataType="Long")
    private Long batchNum;
}

package com.cnuip.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "需求统计VO", description = "需求统计VO")
public class RequirementCountVo{

    @ApiModelProperty(value="需求总数", name="requirementNum", dataType="Long")
    private Long requirementNum;

    @ApiModelProperty(value="待回复需求数", name="noRequirementReplyNum", dataType="Long")
    private Long noRequirementReplyNum;
}

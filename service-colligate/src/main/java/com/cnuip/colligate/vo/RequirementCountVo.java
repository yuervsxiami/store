package com.cnuip.colligate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "需求及留言统计VO", description = "需求及留言统计VO")
public class RequirementCountVo {

    @ApiModelProperty(value="留言总数", name="commentNum", dataType="Long")
    private Long commentNum;

    @ApiModelProperty(value="待回复留言数", name="noCommentReplyNum", dataType="Long")
    private Long noCommentReplyNum;

    @ApiModelProperty(value="需求总数", name="requirementNum", dataType="Long")
    private Long requirementNum;

    @ApiModelProperty(value="待回复需求数", name="noRequirementReplyNum", dataType="Long")
    private Long noRequirementReplyNum;
}

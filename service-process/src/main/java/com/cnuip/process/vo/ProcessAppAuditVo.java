package com.cnuip.process.vo;

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
@ApiModel(value = "提案审核列表VO", description = "提案统计数据VO，包含统计数据")
public class ProcessAppAuditVo {

    @ApiModelProperty(value="提案列表", name="processList", dataType="Long")
    private PageInfo<ProcessListVo> processList;

    @ApiModelProperty(value="提案数", name="processNum", dataType="Long")
    private Long processNum;

    @ApiModelProperty(value="审核数", name="auditNum", dataType="Long")
    private Long auditNum;

    @ApiModelProperty(value="抄送数", name="copyNum", dataType="Long")
    private Long copyNum;
}

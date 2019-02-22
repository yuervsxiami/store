package com.cnuip.process.vo;

import com.cnuip.base.domain.process.Process;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by xjt on 2018/8/22.
 */
@Getter
@Setter
@ApiModel(value = "提案审核列表VO", description = "提案审核列表VO", parent = Process.class)
public class ProcessListVo extends Process {

    @ApiModelProperty(value="审核状态", name="isExamined", dataType="String")
    private String isExamined;

    @ApiModelProperty(value="提案环节ID", name="processTaskId", dataType="String")
    private String processTaskId;
}

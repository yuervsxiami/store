package com.cnuip.colligate.vo;

import com.cnuip.base.domain.process.ProcessTask;
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
@ApiModel(value = "提案环节VO", description = "提案环节VO,包含环节部门,环节审核状态", parent = ProcessTask.class)
public class ProcessTaskVo extends ProcessTask{
    //提案环节模板对应部门
    @ApiModelProperty(value="提案环节对应部门", name="processTaskDepartmentVoList", dataType="ArrayList")
    private List<TmplProcessTaskDepartmentVo> processTaskDepartmentVoList;
}

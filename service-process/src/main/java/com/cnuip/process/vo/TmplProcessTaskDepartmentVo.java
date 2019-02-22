package com.cnuip.process.vo;

import com.cnuip.base.domain.process.ProcessTaskUser;
import com.cnuip.base.domain.process.TmplProcessTaskDepartment;
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
@ApiModel(value = "流程环节模板对应部门VO", description = "流程环节模板对应部门VO,包含流程环节部门,流程环节审核状态、审核人", parent = TmplProcessTaskDepartment.class)
public class TmplProcessTaskDepartmentVo extends TmplProcessTaskDepartment{

    @ApiModelProperty(value="流程环节模板对应部门审核人", name="processTaskUserList", dataType="ArrayList")
    private List<ProcessTaskUser> processTaskUserList;
}

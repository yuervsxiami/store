package com.cnuip.process.vo;

import com.cnuip.base.domain.process.TmplProcessTask;
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
@ApiModel(value = "流程环节模板VO", description = "流程环节模板VO,包含流程环节部门,流程环节审核状态", parent = TmplProcessTask.class)
public class TmplProcessTaskVo extends TmplProcessTask{
    //流程环节模板对应部门
    @ApiModelProperty(value="流程环节模板对应部门", name="tmplProcessTaskDepartmentVoList", dataType="ArrayList")
    private List<TmplProcessTaskDepartmentVo> tmplProcessTaskDepartmentVoList;
}

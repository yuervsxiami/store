package com.cnuip.colligate.vo;

import com.cnuip.base.domain.console.Department;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by xjt on 2018/7/25.
 */
@Data
@ApiModel(value = "部门VO", description = "部门Vo,整理部门树状表", parent = Department.class)
public class DepartmentVo extends Department {
    /** 子权限集合 */
    @ApiModelProperty(value="子权限", name="children", dataType="ArrayList")
    private List<DepartmentVo> children;
}

package com.cnuip.console.vo;

import com.cnuip.base.domain.console.Organization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by xjt on 2018/7/25.
 */
@Data
@ApiModel(value = "组织VO", description = "组织VO", parent = Organization.class)
public class OrganizationVo extends Organization {

    @ApiModelProperty(value="曾用名[数组]", name="usedNames")
    private String[] usedNames;
    @ApiModelProperty(value="研究方向[数组]", name="directions")
    private String[] directions;
}

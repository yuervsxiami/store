package com.cnuip.console.vo;

import com.cnuip.base.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date: 2018/10/8 16:52
 */
@Data
@ApiModel(value = "省份组织VO", description = "省份组织VO", parent = BaseModel.class)
public class ProvinceOrganizationVo extends BaseModel {

    @ApiModelProperty(value="ID", name="id")
    private Long id;

    @ApiModelProperty(value="名称", name="name")
    private String name;

    @ApiModelProperty(value="组织", name="children")
    private List<ProvinceOrganizationVo> children;
}

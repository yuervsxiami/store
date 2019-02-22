package com.cnuip.base.domain.params;

import com.cnuip.base.base.QueryParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "省份表查询实体", description = "省份表查询实体")
public class ProvinceParam extends QueryParam {

    /** 名称 */
    @ApiModelProperty(value="名称", name="name", dataType="String", example="江苏")
    private String name;
    public void setName(String name) { this.name = name; } 
    public String getName() { return this.name; } 

}

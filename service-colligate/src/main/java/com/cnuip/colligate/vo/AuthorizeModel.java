package com.cnuip.colligate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by xjt on 2018/8/22.
 */
@Getter
@Setter
@ApiModel(value = "专利委托查询条件", description = "专利委托查询条件")
public class AuthorizeModel {

    @ApiModelProperty(value = "委托编号", name = "no", dataType = "String")
    private String no;

    @ApiModelProperty(value = "专利ID", name = "authorizePatentId", dataType = "Long")
    private Long authorizePatentId;

    @ApiModelProperty(value = "专利名称", name = "ti", dataType = "String")
    private String ti;

    @ApiModelProperty(value = "委托状态", name = "state", dataType = "String")
    private String state;

    @ApiModelProperty(value = "委托人Id", name = "userId", dataType = "Long")
    private Long userId;

    @ApiModelProperty(value = "委托人", name = "username", dataType = "String")
    private String username;

    @ApiModelProperty(value = "realName", name = "realName", dataType = "String")
    private String realName;

    private Integer pageNum;
    private Integer pageSize;
}

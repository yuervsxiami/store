package com.cnuip.colligate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User:zhaozhihui
 * Date: 2018/11/8.
 * Time: 13:57
 */
@Data
@ApiModel(value = "专利价格VO", description = "专利价格VO")
public class PatentValueVo {
    @ApiModelProperty(value="专利类型", name="type", dataType="String")
    private String type;
    @ApiModelProperty(value="专利数", name="count", dataType="Long")
    private Long count;
    @ApiModelProperty(value="专利价值", name="yxPatentPrice", dataType="String")
    private String price;
}

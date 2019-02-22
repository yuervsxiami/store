package com.cnuip.base.domain.console;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "开屏表", description = "开屏表")
public class Splash implements Serializable {
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;
    @ApiModelProperty(value="图片地址", name="url", dataType="String")
    private String url;
    @ApiModelProperty(value="链接地址", name="url", dataType="String")
    private String hrefUrl;
    @ApiModelProperty(value="生效开始时间", name="showStartTime", dataType="Date")
    private Date showStartTime;
    @ApiModelProperty(value="生效结束时间", name="showEndTime", dataType="Date")
    private Date showEndTime;
    @ApiModelProperty(value="过渡时间", name="transientTime", dataType="BigDecimal")
    private BigDecimal transientTime;
    @ApiModelProperty(value="状态", name="state", dataType="String")
    private String state;
    /** 操作人 mbr_user.id */
    @ApiModelProperty(value="操作人", name="editorId", dataType="Long")
    private Long editorId;

    /** 操作人 mbr_user.username */
    @ApiModelProperty(value="操作人", name="editorName", dataType="String", example="admin")
    private String editorName;

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTime;
}

package com.cnuip.colligate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by xjt on 2018/10/9.
 */
@Getter
@Setter
@ApiModel(value = "专利VO", description = "专利VO")
public class PatentVo {

    @ApiModelProperty(
            value = "申请号/专利名称",
            name = "anOrTi",
            dataType = "String"
    )
    private String anOrTi;

    @ApiModelProperty(
            value = "权利人",
            name = "ph",
            dataType = "String"
    )
    private String ph;

    @ApiModelProperty(
            value = "发明人(用户的realName)",
            name = "pin",
            dataType = "String"
    )
    private String pin;

    @ApiModelProperty(
            value = "专利类型 [ FMSQ、FMZL.发明专利 WGZL.外观专利 SYXX.实用新型 ]",
            name = "sectionName",
            dataType = "String"
    )
    private String sectionName;

    @ApiModelProperty(
            value = "专利状态 [ 在审、有效、失效、有效期满 ]",
            name = "lastLegalStatus",
            dataType = "String"
    )
    private String lastLegalStatus;

    @ApiModelProperty(
            value = "专利价值最大值",
            name = "maxPatentValue",
            dataType = "String"
    )
    private String maxPatentValue;

    @ApiModelProperty(
            value = "专利价值最大值",
            name = "minPatentValue",
            dataType = "String"
    )
    private String minPatentValue;

    @ApiModelProperty(
            value = "专利类型 [实用新型，发明专利，外观专利]",
            name = "type",
            dataType = "String"
    )
    private String type;

    @ApiModelProperty(
            value = "yyyy-MM-dd",
            name = "startTime",
            dataType = "String"
    )
    private String startTime;

    @ApiModelProperty(
            value = "yyyy-MM-dd",
            name = "endTime",
            dataType = "String"
    )
    private String endTime;
    private Integer pageSize;
    private Integer pageNum;

    @ApiModelProperty(
            value = "发明人(合作伙伴名字)",
            name = "pins",
            dataType = "String"
    )
    private String pins;
}

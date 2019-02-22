package com.cnuip.user.vo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequirementQueryParam{
    @ApiModelProperty(
            value = "需求名称",
            name = "title",
            dataType = "String"
    )
    private String title;
    @ApiModelProperty(
            value = "分类编码",
            name = "code",
            dataType = "String"
    )
    private String code;
    @ApiModelProperty(
            value = "开始时间",
            name = "startTime",
            dataType = "java.util.Date"
    )
    private Date startTime;
    @ApiModelProperty(
            value = "结束时间",
            name = "endTime",
            dataType = "java.util.Date"
    )
    private Date endTime;
    @ApiModelProperty(
            value = "是否回复",
            name = "isReply",
            dataType = "String"
    )
    private String isReply;
    @ApiModelProperty(
            value = "用户名",
            name = "userName",
            dataType = "String"
    )
    private String username;
    @ApiModelProperty(
            value = "用户ID",
            name = "userId",
            dataType = "Long"
    )
    private Long userId;
    @ApiModelProperty(
            value = "企业类型(使用英文字符串 HIGH_NEW_TECHNOLOGY.高新技术企业 INNOVATIVE.创新型企业 SCIENCE_TECHNOLOGY.科技型中小企业 PRIVATE_SCIENCE_TECHNOLOGY.民营科技企业 LARGE_MIDDLE_SIZED.大中型企业)",
            name = "enterpriseType",
            dataType = "String"
    )
    private String enterpriseType;

    private int pageNum;
    private int pageSize;
}

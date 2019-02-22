package com.cnuip.base.domain.user;

import com.cnuip.base.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Data
@ApiModel(value = "用户需求表", description = "用户需求表")
public class Requirement extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** CNUIP2中用户需求ID */
    @ApiModelProperty(value="CNUIP2中用户需求ID", name="requirementId", dataType="Long")
    private Long requirementId;

    /** 专家ID mbr_user.id */
    @ApiModelProperty(value="专家ID", name="userId", dataType="Long")
    private Long userId;

    /** 需求内容 */
    @ApiModelProperty(value="需求内容", name="content", dataType="String")
    private String content;

    /** 回复内容 */
    @ApiModelProperty(value="回复内容", name="replyContent", dataType="String")
    private String replyContent;

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTime;


    @Override
    public String checkValue(){

        if (this.getRequirementId() == null){
            return "REQUIREMENT_REQUIREMENT_ID_CANNOT_NULL";
        }
        if (this.getUserId() == null){
            return "REQUIREMENT_USER_ID_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public Requirement setDefaultValue(){


        return this;
    }
}

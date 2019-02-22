package com.cnuip.base.domain.console;

import com.cnuip.base.base.BaseModel;
import com.cnuip.base.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Data
@ApiModel(value = "项目组表", description = "项目组表")
public class Team extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;

    /** 名称 */
    @ApiModelProperty(value="名称", name="name", dataType="String", example="项目组一")
    private String name;

    /** 备注 */
    @ApiModelProperty(value="备注", name="remark", dataType="String")
    private String remark;

    /** 是否删除 [ YES.是 NO.否 ] */
    /** YesNoEnum */
    @ApiModelProperty(value="是否删除 [ YES.是 NO.否 ]", name="isDelete", dataType="String", example="NO")
    private String isDelete;

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


    @Override
    public String checkValue(){

        if (this.getOrganizationId() == null){
            return "TEAM_ORGANIZATION_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getName())){
            return "TEAM_NAME_CANNOT_NULL";
        }
        if (this.getName() == null){
            return "TEAM_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getName()) > 255){
            return "TEAM_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getRemark()) > 255){
            return "TEAM_REMARK_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getIsDelete())){
            return "TEAM_IS_DELETE_CANNOT_NULL";
        }
        if (this.getIsDelete() == null){
            return "TEAM_IS_DELETE_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public Team setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getIsDelete())){
            this.setIsDelete("NO");
        }

        return this;
    }
}

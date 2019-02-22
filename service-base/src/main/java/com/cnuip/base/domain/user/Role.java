package com.cnuip.base.domain.user;

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
@ApiModel(value = "角色表", description = "角色表")
public class Role extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;

    /** 名称 */
    @ApiModelProperty(value="名称", name="name", dataType="String", example="ROLE_ADMIN")
    private String name;

    /** 备注 */
    @ApiModelProperty(value="备注", name="remark", dataType="String", example="超级用户组")
    private String remark;

    /** 是否删除 [ YES.是 NO.否 ] */
    /** YesNoEnum */
    @ApiModelProperty(value="是否删除 [ YES.是 NO.否 ]", name="isDelete", dataType="String", example="NO")
    private String isDelete;

    /** 操作人ID mbr_user.id */
    @ApiModelProperty(value="操作人ID", name="editorId", dataType="Long")
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
            return "ROLE_ORGANIZATION_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getName())){
            return "ROLE_NAME_CANNOT_NULL";
        }
        if (this.getName() == null){
            return "ROLE_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getName()) > 255){
            return "ROLE_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getRemark()) > 255){
            return "ROLE_REMARK_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getIsDelete())){
            return "ROLE_IS_DELETE_CANNOT_NULL";
        }
        if (this.getIsDelete() == null){
            return "ROLE_IS_DELETE_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public Role setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getIsDelete())){
            this.setIsDelete("NO");
        }

        return this;
    }
}

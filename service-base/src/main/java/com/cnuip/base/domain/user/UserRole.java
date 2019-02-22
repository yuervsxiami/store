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
@ApiModel(value = "用户角色关系表", description = "用户角色关系表")
public class UserRole extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 用户ID mbr_user.id */
    @ApiModelProperty(value="用户ID", name="userId", dataType="Long")
    private Long userId;

    /** 角色ID mbr_role.id */
    @ApiModelProperty(value="角色ID", name="roleId", dataType="Long")
    private Long roleId;


    @Override
    public String checkValue(){

        if (this.getUserId() == null){
            return "USERROLE_USER_ID_CANNOT_NULL";
        }
        if (this.getRoleId() == null){
            return "USERROLE_ROLE_ID_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public UserRole setDefaultValue(){


        return this;
    }
}

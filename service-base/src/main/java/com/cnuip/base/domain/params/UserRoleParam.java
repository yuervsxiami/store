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
@ApiModel(value = "用户角色关系表查询实体", description = "用户角色关系表查询实体")
public class UserRoleParam extends QueryParam {

    /** 用户ID mbr_user.id */
    @ApiModelProperty(value="用户ID", name="userId", dataType="Long")
    private Long userId;
    public void setUserId(Long userId) { this.userId = userId; } 
    public Long getUserId() { return this.userId; } 

    /** 角色ID mbr_role.id */
    @ApiModelProperty(value="角色ID", name="roleId", dataType="Long")
    private Long roleId;
    public void setRoleId(Long roleId) { this.roleId = roleId; } 
    public Long getRoleId() { return this.roleId; } 

}

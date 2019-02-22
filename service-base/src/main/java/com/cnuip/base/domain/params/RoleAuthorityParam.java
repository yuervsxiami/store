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
@ApiModel(value = "角色权限对应表查询实体", description = "角色权限对应表查询实体")
public class RoleAuthorityParam extends QueryParam {

    /** mbr_role.id */
    @ApiModelProperty(name="roleId", dataType="Long")
    private Long roleId;
    public void setRoleId(Long roleId) { this.roleId = roleId; } 
    public Long getRoleId() { return this.roleId; } 

    /** mbr_authority.id */
    @ApiModelProperty(name="authorityId", dataType="Long")
    private Long authorityId;
    public void setAuthorityId(Long authorityId) { this.authorityId = authorityId; } 
    public Long getAuthorityId() { return this.authorityId; } 

}

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
@ApiModel(value = "角色权限对应表", description = "角色权限对应表")
public class RoleAuthority extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** mbr_role.id */
    @ApiModelProperty(value="", name="roleId", dataType="Long")
    private Long roleId;

    /** mbr_authority.id */
    @ApiModelProperty(value="", name="authorityId", dataType="Long")
    private Long authorityId;


    @Override
    public String checkValue(){


        return null;
    }

    @Override
    public RoleAuthority setDefaultValue(){


        return this;
    }
}

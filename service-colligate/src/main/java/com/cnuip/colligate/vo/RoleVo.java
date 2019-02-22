package com.cnuip.colligate.vo;

import com.cnuip.base.domain.user.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@ApiModel(value = "角色VO", description = "角色Vo", parent = Role.class)
public class RoleVo extends Role {
    @ApiModelProperty(value="权限", name="authorityVos", dataType="ArrayList")
    private List<AuthorityVo> authorityVos;
}

package com.cnuip.colligate.vo;

import com.cnuip.base.domain.user.Role;
import com.cnuip.base.domain.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "用户VO", description = "用户VO", parent = User.class)
public class UserVo extends User {
    @ApiModelProperty(value = "角色", name = "roles", dataType = "ArrayList")
    private List<Role> roles;
}

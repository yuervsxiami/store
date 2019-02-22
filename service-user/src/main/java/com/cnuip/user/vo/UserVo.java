package com.cnuip.user.vo;

import com.cnuip.base.domain.console.Team;
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
    @ApiModelProperty(value = "权限", name = "authorities", dataType = "ArrayList")
    private List<AuthorityVo> authorities;
    @ApiModelProperty(value = "项目组", name = "teams", dataType = "ArrayList")
    private List<Team> teams;
    @ApiModelProperty(value = "登录类型", name = "loginTypeEnum")
    private LoginTypeEnum loginTypeEnum;
    @ApiModelProperty(value = "短信认证码", name = "verifyCode")
    private String verifyCode;
}

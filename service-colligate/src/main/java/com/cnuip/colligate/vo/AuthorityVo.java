package com.cnuip.colligate.vo;

import com.cnuip.base.domain.user.Authority;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "权限VO", description = "权限Vo", parent = Authority.class)
public class AuthorityVo extends Authority {
    private List<AuthorityVo> children;
}

package com.cnuip.user.service;

import com.cnuip.base.domain.params.RoleParam;
import com.cnuip.base.domain.user.Role;
import com.cnuip.base.service.AbstractService;
import com.cnuip.user.rest.exceptions.CustomException;
import com.cnuip.user.vo.RoleVo;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface RoleService extends AbstractService<Role, RoleParam> {
    Role addRole(RoleVo roleVo) throws CustomException;

    String deleteRole(Long roleId) throws Exception;

    RoleVo updateRole(RoleVo roleVo) throws Exception;

    PageInfo<RoleVo> queryRole(RoleParam roleParam) throws Exception;

    RoleVo queryDetail(Role e);
}

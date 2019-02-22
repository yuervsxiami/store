package com.cnuip.user.service.impl;

import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.RoleParam;
import com.cnuip.base.domain.user.Authority;
import com.cnuip.base.domain.user.Role;
import com.cnuip.base.domain.user.RoleAuthority;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.utils.PageInfoTransferUtils;
import com.cnuip.base.utils.TreeUtils;
import com.cnuip.user.repository.AuthorityMapper;
import com.cnuip.user.repository.RoleMapper;
import com.cnuip.user.repository.base.AuthorityBaseMapper;
import com.cnuip.user.repository.base.RoleAuthorityBaseMapper;
import com.cnuip.user.repository.base.RoleBaseMapper;
import com.cnuip.user.rest.exceptions.CustomException;
import com.cnuip.user.rest.exceptions.ResponseEnum;
import com.cnuip.user.service.RoleService;
import com.cnuip.user.vo.AuthorityVo;
import com.cnuip.user.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 王志斌
 * @Date: 2018/4/3 15:27
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl
        extends AbstractServiceImpl<Role, RoleParam>
        implements RoleService {

    @Autowired
    private RoleBaseMapper roleBaseMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleAuthorityBaseMapper roleAuthorityBaseMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private AuthorityBaseMapper authorityBaseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role addRole(RoleVo roleVo) throws CustomException {
        RoleParam roleParam = new RoleParam();
        roleParam.setOrganizationId(roleVo.getOrganizationId());
        roleParam.setName(roleVo.getName());
        Role role = roleBaseMapper.selectOne(roleParam);
        if(role!=null){
            throw new CustomException(ResponseEnum.ROLE_INSERT_EXIST_ERROR);
        }
        roleBaseMapper.insert(roleVo);
        List<AuthorityVo> authorityVos = roleVo.getAuthorityVos();
        insertRoleAuthority(authorityVos, roleVo.getId());
        return roleVo;
    }

    private void insertRoleAuthority(List<AuthorityVo> authorityVos, Long roleId) {
        for (AuthorityVo e : authorityVos) {
            insertAllAuth(roleId, e.getId());
        }
    }

    private void insertAllAuth(Long roleId, Long id) {
        Authority authority = authorityBaseMapper.selectOneByKey(id);
        if (authority != null && authority.getId() != null) {
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setRoleId(roleId);
            roleAuthority.setAuthorityId(authority.getId());
            roleAuthorityBaseMapper.insert(roleAuthority);
            if (authority.getParentId() != null && authority.getParentId() != 0) {
                insertAllAuth(roleId, authority.getParentId());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteRole(Long roleId) throws Exception {
        // 查询该角色是否被使用
        List<User> userList = roleMapper.searchUser(roleId);
        if(userList != null && userList.size() > 0){
            throw  new CustomException(ResponseEnum.ROLE_DELETE_ERROR);
        }
        this.updateFieldByKey(roleId, "isDelete", YesNoEnum.YES.toString());
        roleAuthorityBaseMapper.deleteByField("role_id", roleId, null);
        return "success";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleVo updateRole(RoleVo roleVo) throws Exception {
        Role role = this.updateByKey(roleVo.getId(), roleVo);
        roleAuthorityBaseMapper.deleteByField("role_id", role.getId(), null);
        List<AuthorityVo> authorityVos = roleVo.getAuthorityVos();
        insertRoleAuthority(authorityVos, role.getId());
        return roleVo;
    }

    @Override
    public PageInfo<RoleVo> queryRole(RoleParam roleParam) throws Exception {
        PageInfo<Role> rolePageInfo = this.selectMany(roleParam);
        List<Role> roles = rolePageInfo.getList();
        List<RoleVo> roleVos = new ArrayList<>();
        for (Role e : roles) {
            roleVos.add(this.queryDetail(e));
        }
        return PageInfoTransferUtils.transfer(rolePageInfo, roleVos);
    }

    @Override
    public RoleVo queryDetail(Role e) {
        List<AuthorityVo> authorityVos = TreeUtils.listToTree(authorityMapper.selectAuthorityVos(e.getId()));
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(e, roleVo);
        roleVo.setAuthorityVos(authorityVos);
        return roleVo;
    }
}
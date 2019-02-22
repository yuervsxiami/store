package com.cnuip.colligate.service.impl;

import com.cnuip.base.domain.console.Organization;
import com.cnuip.base.domain.params.UserParam;
import com.cnuip.base.domain.user.Role;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.ClientServiceUtils;
import com.cnuip.colligate.client.ConsoleClient;
import com.cnuip.colligate.client.UserClient;
import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.exception.enums.ClientEnum;
import com.cnuip.colligate.service.OrganizationService;
import com.cnuip.colligate.vo.AuthorityVo;
import com.cnuip.colligate.vo.OrganizationVo;
import com.cnuip.colligate.vo.RoleVo;
import com.cnuip.colligate.vo.UserVo;
import com.cnuip.colligate.vo.domain.Shop;
import com.cnuip.gaea.service.web.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 王志斌
 * @Date: 2018/4/3 15:27
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private ConsoleClient organizationClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.cnuip.url}")
    private String shopUrl;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Organization addOrganization(Long editorId, String editorName, Shop shop) throws Exception {
        OrganizationVo organization = new OrganizationVo();
        organization.setEditorId(editorId);
        organization.setEditorName(editorName);
        organization.setName(shop.getPa());
        organization.setLogoUrl(shop.getImageUrl());
        organization.setIntroduction(shop.getIntroduction());
        organization.setProvinceName(shop.getIdCardNo());
        organization.setAddress(shop.getAddress());
        organization.setDirection(shop.getDirection());
        organization.setHonor(shop.getHonor());
        // 新增组织
        Organization org = this.handleObject(organization);
        ApiResponse<Organization> organizationRes = new ClientServiceUtils<Organization, ConsoleClient>().exec(organizationClient, "add", organization.getEditorId(),organization.getEditorName(),organization);
        if(organizationRes.getCode() != 0){
            throw new ClientException(ClientEnum.ORGANIZATION_ERROR);
        }
        // 新增该组织的ROLE_ADMIN角色和ROLE_NORMAL角色
        Role role = addRole(organizationRes.getResult());
        // 新增该组织的admin用户
        User user = addUser(organizationRes.getResult(),role);
        // 修改cnuip2中shop的remoteKey
        LinkedHashMap shopMap;
        try{
            Shop shopEditor = new Shop();
            shopEditor.setId(shop.getId());
            shopEditor.setRemoteKey(user.getId().toString());
            ResponseEntity<Map> responseEntity = restTemplate.exchange(shopUrl + "cnuip2-mservice-shop/v1/shop/byid", HttpMethod.PUT, new HttpEntity<>(shopEditor), Map.class);
            shopMap = (LinkedHashMap) responseEntity.getBody().get("result");
        }catch (Exception e){
            throw new ClientException(ClientEnum.CNUIP_CLIENT_SHOP_UPDATE_ERROR);
        }
        return org;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Organization updateOrg(OrganizationVo organization) throws Exception{

        ApiResponse<Organization> organizationRes = new ClientServiceUtils<Organization, ConsoleClient>().exec(organizationClient, "updateOrg", organization.getId(),organization);
        if(organizationRes.getCode() != 0){
            throw new ClientException(organizationRes.getCode(),organizationRes.getMessage());
        }
        // 查询该组织下的admin用户
        UserParam userParam = new UserParam();
        userParam.setOrganizationId(organization.getId());
        userParam.setUsername("admin");
        ApiResponse<User> userRes = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryAdminUser",organization.getId());
        if(userRes.getCode() != 0){
            throw new ClientException(userRes.getCode(),userRes.getMessage());
        }
        if(userRes.getResult() != null && userRes.getResult() != null){
            LinkedHashMap shoMap;
            try{
                Shop shop = new Shop();
                shop.setRemoteKey(userRes.getResult().getId().toString());
                shop.setImageUrl(organization.getLogoUrl());
                shop.setIntroduction(organization.getIntroduction());
                shop.setAddress(organization.getAddress());
                shop.setDirection(organization.getDirection());
                shop.setHonor(organization.getHonor());
                ResponseEntity<Map> responseEntity = restTemplate.exchange(shopUrl + "cnuip2-mservice-shop/v1/shop/byremotekey", HttpMethod.PUT, new HttpEntity<>(shop), Map.class);
                shoMap = (LinkedHashMap) responseEntity.getBody().get("result");
            }catch (Exception e){
                throw new ClientException(ClientEnum.CNUIP_CLIENT_SHOP_UPDATE_ERROR);
            }
        }

        return organizationRes.getResult();
    }

    private Organization handleObject(OrganizationVo organization){
        String[] directions = organization.getDirections();
        String[] usedNames = organization.getUsedNames();
        StringBuilder direction = new StringBuilder();
        StringBuilder usedName = new StringBuilder();
        Organization org = new Organization();
        BeanUtils.copyProperties(organization, org);
        if (directions != null && directions.length > 0) {
            for (String e : directions) {
                direction.append(e + ",");
            }
            direction.deleteCharAt(direction.length() - 1);
            org.setDirection(direction.toString());
        }
        if (usedNames != null && usedNames.length > 0) {
            for (String e : usedNames) {
                usedName.append(e + ",");
            }
            usedName.deleteCharAt(usedName.length() - 1);
            org.setUsedName(usedName.toString());
        }
        return org;
    }

    private Role addRole(Organization organization) throws Exception{

        //新增ROLE_ADMIN角色
        RoleVo roleAdmin = new RoleVo();
        roleAdmin.setName("ROLE_ADMIN");
        // 查询权限树
        ApiResponse<List<AuthorityVo>> authorityRes = new ClientServiceUtils<List<AuthorityVo>, UserClient>().exec(userClient, "queryAuth", null);
        if(authorityRes.getCode() != 0){
            throw new ClientException(authorityRes.getCode(),authorityRes.getMessage());
        }
        roleAdmin.setAuthorityVos(authorityRes.getResult());
        ApiResponse<Role> roleAdminRes = new ClientServiceUtils<Role, UserClient>().exec(userClient, "addRole", organization.getEditorId(),organization.getEditorName(),organization.getId(),roleAdmin);
        if(roleAdminRes.getCode() != 0){
            throw new ClientException(roleAdminRes.getCode(),roleAdminRes.getMessage());
        }
        //新增ROLE_NORMAL角色
        RoleVo roleNormal = new RoleVo();
        roleNormal.setName("ROLE_NORMAL");
        ApiResponse<Role> roleNormalRes = new ClientServiceUtils<Role, UserClient>().exec(userClient, "addRole", organization.getEditorId(),organization.getEditorName(),organization.getId(),roleNormal);
        if(roleNormalRes.getCode() != 0){
            throw new ClientException(roleNormalRes.getCode(),roleNormalRes.getMessage());
        }

        return roleAdminRes.getResult();
    }

    private User addUser(Organization organization,Role role) throws Exception{
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        UserVo userVo = new UserVo();
        userVo.setUsername("admin");
        userVo.setRoles(roles);
        userVo.setPassword("123456");
        ApiResponse<User> userRes = new ClientServiceUtils<User, UserClient>().exec(userClient, "add", organization.getEditorId(),organization.getEditorName(),organization.getId(),organization.getName(),userVo);
        if(userRes.getCode() != 0){
            throw new ClientException(userRes.getCode(),userRes.getMessage());
        }
        return userRes.getResult();
    }
}
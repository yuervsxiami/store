package com.cnuip.console.service.impl;

import com.cnuip.base.domain.console.Organization;
import com.cnuip.base.domain.console.Province;
import com.cnuip.base.domain.params.OrganizationParam;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.utils.StringUtils;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.console.repository.base.OrganizationBaseMapper;
import com.cnuip.console.repository.base.ProvinceBaseMapper;
import com.cnuip.console.service.OrganizationService;
import com.cnuip.console.vo.OrganizationVo;
import com.cnuip.console.vo.ProvinceOrganizationVo;
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
public class OrganizationServiceImpl extends AbstractServiceImpl<Organization, OrganizationParam> implements OrganizationService {

    @Autowired
    private ProvinceBaseMapper provinceBaseMapper;

    @Autowired
    private OrganizationBaseMapper organizationBaseMapper;

    @Override
    public List<OrganizationVo> getAllVo(OrganizationParam o) {
        List<Organization> all = this.getAll(o);
        List<OrganizationVo> organizationVos = new ArrayList<>();
        for (Organization e : all) {
            OrganizationVo organizationVo = new OrganizationVo();
            BeanUtils.copyProperties(e, organizationVo);
            if (!StringUtils.isNullOrEmpty(e.getDirection())) {
                organizationVo.setDirections(e.getDirection().split(","));
            }
            if (!StringUtils.isNullOrEmpty(e.getUsedName())) {
                organizationVo.setUsedNames(e.getUsedName().split(","));
            }
            organizationVos.add(organizationVo);
        }
        return organizationVos;
    }

    @Override
    public OrganizationVo selectVo(Long orgId) {
        Organization organization = this.selectOneByKey(orgId);
        OrganizationVo organizationVo = new OrganizationVo();
        BeanUtils.copyProperties(organization, organizationVo);
        if (!StringUtils.isNullOrEmpty(organization.getDirection())) {
            organizationVo.setDirections(organization.getDirection().split(","));
        }
        if (!StringUtils.isNullOrEmpty(organization.getUsedName())) {
            organizationVo.setUsedNames(organization.getUsedName().split(","));
        }
        return organizationVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Organization addOrganization(OrganizationVo organization) throws Exception {
        Organization org = this.handleObject(organization);
        this.check(org);
        organizationBaseMapper.insert(org);
        return org;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Organization updateVo(OrganizationVo organization) throws Exception {
        //检查组织默认值
        String checkValue = organization.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        Organization org = this.handleObject(organization);
        organizationBaseMapper.updateByKey(organization.getId(), org);
        return organizationBaseMapper.selectOneByKey(organization.getId());
    }

    @Override
    public List<ProvinceOrganizationVo> getProvinceOrganizationTree() {
        List<ProvinceOrganizationVo> provinceOrganizationVos = new ArrayList<>();
        List<Province> provinces = provinceBaseMapper.getAll(null);
        for (Province province : provinces) {
            OrganizationParam organizationParam = new OrganizationParam();
            organizationParam.setProvinceId(province.getId());
            List<ProvinceOrganizationVo> children = new ArrayList<>();
            List<Organization> organizations = organizationBaseMapper.getAll(organizationParam);
            for (Organization organization : organizations) {
                ProvinceOrganizationVo child = new ProvinceOrganizationVo();
                child.setId(organization.getId());
                child.setName(organization.getName());
                children.add(child);
            }

            if (children.size()>0){
                ProvinceOrganizationVo parent = new ProvinceOrganizationVo();
                parent.setId(province.getId());
                parent.setName(province.getName());
                parent.setChildren(children);
                provinceOrganizationVos.add(parent);
            }
        }
        return provinceOrganizationVos;
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
        }else if(usedName.length() == 0){
            org.setUsedName("");
        }
        return org;
    }
}
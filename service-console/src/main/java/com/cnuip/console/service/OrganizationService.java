package com.cnuip.console.service;


import com.cnuip.base.domain.console.Organization;
import com.cnuip.base.domain.params.OrganizationParam;
import com.cnuip.base.service.AbstractService;
import com.cnuip.console.vo.OrganizationVo;
import com.cnuip.console.vo.ProvinceOrganizationVo;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface OrganizationService extends AbstractService<Organization, OrganizationParam> {
    List<OrganizationVo> getAllVo(OrganizationParam o);

    OrganizationVo selectVo(Long orgId);

    Organization updateVo(OrganizationVo organization) throws Exception;

    List<ProvinceOrganizationVo> getProvinceOrganizationTree();

    Organization addOrganization(OrganizationVo organization) throws Exception;
}

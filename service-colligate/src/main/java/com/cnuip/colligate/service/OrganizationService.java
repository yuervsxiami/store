package com.cnuip.colligate.service;

import com.cnuip.base.domain.console.Organization;
import com.cnuip.colligate.vo.OrganizationVo;
import com.cnuip.colligate.vo.domain.Shop;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface OrganizationService {

    Organization addOrganization(Long editorId, String editorName, Shop shop) throws Exception;

    Organization updateOrg(OrganizationVo organization) throws Exception;
}

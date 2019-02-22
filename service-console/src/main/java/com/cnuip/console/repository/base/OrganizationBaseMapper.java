package com.cnuip.console.repository.base;

import com.cnuip.base.domain.console.Organization;
import com.cnuip.base.domain.params.OrganizationParam;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface OrganizationBaseMapper extends AbstractMapper<Organization, OrganizationParam>
{}
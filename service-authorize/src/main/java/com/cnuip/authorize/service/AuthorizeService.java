package com.cnuip.authorize.service;

import com.cnuip.authorize.vo.AuthorizeModel;
import com.cnuip.authorize.vo.AuthorizeVo;
import com.cnuip.base.domain.authorize.Authorize;
import com.cnuip.base.domain.params.AuthorizeParam;
import com.cnuip.base.service.AbstractService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by xjt on 2018/8/30.
 */
public interface AuthorizeService extends AbstractService<Authorize, AuthorizeParam> {

    /**
     * 查询专利委托详情
     * @param authorizeId
     * @return
     */
    AuthorizeVo selectAuthorizeDetail(Long authorizeId);

    PageInfo<AuthorizeVo> search(Long orgId, AuthorizeModel authorizeModel);

    /**
     * 新增委托合同
     * @param authorizeVo
     * @return
     */
    AuthorizeVo addAuthorize(AuthorizeVo authorizeVo) throws Exception;

    String updateState(String remoteId, String state) throws Exception;

    List<String> searchAn(String editorName, String orgName);
}

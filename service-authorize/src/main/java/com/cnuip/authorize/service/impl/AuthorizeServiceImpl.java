package com.cnuip.authorize.service.impl;

import com.cnuip.authorize.repository.AuthorizeMapper;
import com.cnuip.authorize.repository.AuthorizePatentMapper;
import com.cnuip.authorize.repository.base.AuthorizeBaseMapper;
import com.cnuip.authorize.repository.base.AuthorizePatentBaseMapper;
import com.cnuip.authorize.rest.exceptions.CustomException;
import com.cnuip.authorize.rest.exceptions.ResponseEnum;
import com.cnuip.authorize.service.AuthorizePatentService;
import com.cnuip.authorize.service.AuthorizeService;
import com.cnuip.authorize.vo.AuthorizeModel;
import com.cnuip.authorize.vo.AuthorizeVo;
import com.cnuip.base.domain.authorize.Authorize;
import com.cnuip.base.domain.authorize.AuthorizePatent;
import com.cnuip.base.domain.params.AuthorizeParam;
import com.cnuip.base.domain.params.AuthorizePatentParam;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.gaea.service.web.ApiResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjt on 2018/8/30.
 */
@Service
public class AuthorizeServiceImpl extends AbstractServiceImpl<Authorize, AuthorizeParam> implements AuthorizeService {

    @Autowired
    private AuthorizeBaseMapper authorizeBaseMapper;

    @Autowired
    private AuthorizePatentBaseMapper authorizePatentBaseMapper;

    @Autowired
    private AuthorizeMapper authorizeMapper;

    @Autowired
    private AuthorizePatentMapper authorizePatentMapper;

    @Autowired
    private AuthorizePatentService authorizePatentService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cnuip.url}")
    private String url;

    @Override
    public AuthorizeVo selectAuthorizeDetail(Long authorizeId) {
        AuthorizeVo authorizeVo = new AuthorizeVo();
        //查询委托合同
        Authorize authorize = authorizeBaseMapper.selectOneByKey(authorizeId);
        if (authorize == null) {
            return null;
        }
        BeanUtils.copyProperties(authorize, authorizeVo);
        //查询委托合同中的专利列表
        AuthorizePatentParam authorizePatentParam = new AuthorizePatentParam();
        authorizePatentParam.setAuthorizeId(authorizeId);
        List<AuthorizePatent> authorizePatentList = authorizePatentBaseMapper.getAll(authorizePatentParam);
        authorizeVo.setAuthorizePatentList(authorizePatentList);
        return authorizeVo;
    }

    @Override
    public PageInfo<AuthorizeVo> search(Long orgId, AuthorizeModel authorizeModel) {
        //查询委托合同
        Integer pageNum = authorizeModel.getPageNum();
        if (pageNum == null) {
            pageNum = 1;
        }
        Integer pageSize = authorizeModel.getPageSize();
        if (pageSize == null) {
            pageSize = 10;
        }

        List<Authorize> authorizeList = authorizeMapper.selectAuthorizeList(orgId,authorizeModel, pageNum, pageSize);
        PageInfo<Authorize> authorizePage = ((Page<Authorize>) authorizeList).toPageInfo();
        if (authorizePage != null) {
            PageInfo<AuthorizeVo> authorizeVoPage = new PageInfo<>();
            BeanUtils.copyProperties(authorizePage, authorizeVoPage);
            //查询委托合同中的专利列表
            List<AuthorizeVo> authorizeVoList = new ArrayList<>();
            for (Authorize authorize : authorizePage.getList()) {
                AuthorizeVo authorizeVo = new AuthorizeVo();
                BeanUtils.copyProperties(authorize, authorizeVo);
                AuthorizePatentParam authorizePatentParam = new AuthorizePatentParam();
                authorizePatentParam.setAuthorizeId(authorize.getId());
                List<AuthorizePatent> authorizePatentList = authorizePatentBaseMapper.getAll(authorizePatentParam);
                authorizeVo.setAuthorizePatentList(authorizePatentList);
                authorizeVoList.add(authorizeVo);
            }
            authorizeVoPage.setList(authorizeVoList);
            return authorizeVoPage;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthorizeVo addAuthorize(AuthorizeVo authorizeVo) throws Exception {
        //新增委托合同
        this.check(authorizeVo);
        AuthorizeParam authorizeParam = new AuthorizeParam();
        authorizeParam.setOrganizationId(authorizeVo.getOrganizationId());
        authorizeParam.setNo(authorizeVo.getNo());
        Authorize authorize = this.selectOne(authorizeParam);
        if (authorize != null) {
            throw new CustomException("委托单号已经存在!");
        }
        authorizeBaseMapper.insert(authorizeVo);
        List<AuthorizePatent> authorizePatentList = authorizeVo.getAuthorizePatentList();
        if (authorizePatentList != null && authorizePatentList.size() > 0) {
            for (AuthorizePatent authorizePatent : authorizePatentList) {
                authorizePatent.setAuthorizeId(authorizeVo.getId());
                authorizePatentService.check(authorizePatent);
                AuthorizePatent an = authorizePatentMapper.checkAn(authorizeVo.getOrganizationId(), authorizePatent.getAn());
                if (an != null) {
                    throw new CustomException("专利号为:" + an.getAn() + "的专利已经被委托");
                }
                authorizePatentService.insert(authorizePatent);
            }
        }
        return authorizeVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateState(String remoteId, String state) throws Exception {
        Authorize authorize = this.selectOneByKey(Long.valueOf(remoteId));
        if (authorize == null) {
            throw new CustomException(ResponseEnum.AUTHORIZE_NULL);
        }
        String oldState = authorize.getState();
        Authorize one = this.updateFieldByKey(Long.valueOf(remoteId), "state", state);
        if (oldState.equals("EXAMINING") && state.equals("CANCELLED")) {
            ResponseEntity<ApiResponse> exchange = restTemplate.exchange(url + "/cnuip2-mservice-shop/v1/authorize/unexamined?remoteId={remoteId}", HttpMethod.PUT, null, ApiResponse.class, remoteId);
            if (200 == exchange.getStatusCodeValue()) {
                return "修改成功!将该委托状态由" + oldState + "变更为" + one.getState();
            }
        }
        if (state.equals("CANCELLED") && (!oldState.equals("EXAMINING"))) {
            throw new CustomException("委托状态错误,不可取消!");
        }
        return "修改成功!将该委托状态由" + oldState + "变更为" + one.getState();
    }

    @Override
    public List<String> searchAn(String editorName, String orgName) {
        return authorizeMapper.searchAn(editorName,orgName);
    }
}

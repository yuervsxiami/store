package com.cnuip.process.service;


import com.cnuip.base.domain.params.TmplProcessParam;
import com.cnuip.base.domain.process.TmplProcess;
import com.cnuip.base.service.AbstractService;
import com.cnuip.process.vo.TmplProcessVo;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface TmplProcessService extends AbstractService<TmplProcess, TmplProcessParam> {
    /**
     * 查询流程模板详情
     * @param tmplProcessId
     * @return
     */
    TmplProcessVo selectTmplProcessDetail(Long tmplProcessId) throws Exception;

    TmplProcessVo addTmplProcess(TmplProcessVo tmplProcessVo) throws Exception;

    TmplProcessVo updateTmplProcess(TmplProcessVo tmplProcessVo) throws Exception;
}

package com.cnuip.process.service;

import com.cnuip.base.domain.params.TmplProcessTaskParam;
import com.cnuip.base.domain.process.TmplProcessTask;
import com.cnuip.base.service.AbstractService;
import com.cnuip.process.vo.TmplProcessTaskVo;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface TmplProcessTaskService extends AbstractService<TmplProcessTask, TmplProcessTaskParam> {

    /**
     * 查询流程环节模板信息
     * @param tmplProcessId
     * @return
     */
    List<TmplProcessTaskVo> selectTmplProcessTaskDetail(Long tmplProcessId) throws Exception;

    /**
     * 新增流程环节模板信息
     * @param tmplProcessTaskVo
     * @return
     */
    TmplProcessTaskVo addTmplProcessTask(TmplProcessTaskVo tmplProcessTaskVo) throws Exception;

    /**
     * 修改流程环节模板信息
     * @param tmplProcessTaskVo
     * @return
     */
    TmplProcessTaskVo updateTmplProcessTask(TmplProcessTaskVo tmplProcessTaskVo) throws Exception;

    /**
     * 删除流程环节模板
     * @param tmplProcessTaskId
     * @return
     */
    TmplProcessTaskVo deleteTmplProcessTask(Long tmplProcessTaskId) throws Exception;
}

package com.cnuip.process.service;


import com.cnuip.base.domain.params.TmplProcessTaskDepartmentParam;
import com.cnuip.base.domain.process.TmplProcessTaskDepartment;
import com.cnuip.base.service.AbstractService;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface TmplProcessTaskDepartmentService extends AbstractService<TmplProcessTaskDepartment, TmplProcessTaskDepartmentParam> {
    /**
     * 查询流程环节模板对应部门详情
     * @param tmplProcessTaskId
     * @return
     */
    List<TmplProcessTaskDepartment> selectTmplProcessTaskDepartmentDetail(Long tmplProcessTaskId) throws Exception;
}

package com.cnuip.process.service.impl;


import com.cnuip.base.domain.params.TmplProcessTaskDepartmentParam;
import com.cnuip.base.domain.process.TmplProcessTaskDepartment;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.process.repository.TmplProcessTaskDepartmentMapper;
import com.cnuip.process.repository.base.TmplProcessTaskDepartmentBaseMapper;
import com.cnuip.process.service.TmplProcessTaskDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class TmplProcessTaskDepartmentServiceImpl extends AbstractServiceImpl<TmplProcessTaskDepartment, TmplProcessTaskDepartmentParam> implements TmplProcessTaskDepartmentService {

    @Autowired
    private TmplProcessTaskDepartmentBaseMapper tmplProcessTaskDepartmentBaseMapper;

    @Autowired
    private TmplProcessTaskDepartmentMapper tmplProcessTaskDepartmentMapper;

    @Override
    public List<TmplProcessTaskDepartment> selectTmplProcessTaskDepartmentDetail(Long tmplProcessTaskId) throws Exception{
        TmplProcessTaskDepartmentParam tmplProcessTaskDepartmentParam = new TmplProcessTaskDepartmentParam();
        tmplProcessTaskDepartmentParam.setTmplProcessTaskId(tmplProcessTaskId);
        return tmplProcessTaskDepartmentBaseMapper.getAll(tmplProcessTaskDepartmentParam);
    }
}
package com.cnuip.console.service;

import com.cnuip.base.domain.console.Department;
import com.cnuip.base.domain.params.DepartmentParam;
import com.cnuip.base.service.AbstractService;
import com.cnuip.console.vo.DepartmentVo;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface DepartmentService extends AbstractService<Department, DepartmentParam> {
    /**
     * 查询部门树状表
     * @return
     * @throws Exception
     */
    List<DepartmentVo> getAllDepartmentTree(Long organizationId) throws Exception;

    List<Department> getAllDepartmentList(Long organizationId, String keyWords);

    List<DepartmentVo> getAllDepartmentTreeExamine(Long organizationId);

    List<Department> getExamineList(List<Long> deptIds);

    Department updateDepartment(Department department) throws Exception;

    Department addDepartment(Department department) throws Exception;

    Department deleteDept(Long id) throws Exception;
}

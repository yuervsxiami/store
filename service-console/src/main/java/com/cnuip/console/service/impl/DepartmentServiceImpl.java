package com.cnuip.console.service.impl;

import com.cnuip.base.base.LikeModeEnum;
import com.cnuip.base.domain.console.Department;
import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.DepartmentParam;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.utils.TreeUtils;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.console.repository.DepartmentMapper;
import com.cnuip.console.repository.base.DepartmentBaseMapper;
import com.cnuip.console.rest.exceptions.CustomException;
import com.cnuip.console.rest.exceptions.ResponseEnum;
import com.cnuip.console.service.DepartmentService;
import com.cnuip.console.vo.DepartmentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 王志斌
 * @Date: 2018/4/3 15:27
 */
@Service
public class DepartmentServiceImpl extends AbstractServiceImpl<Department, DepartmentParam> implements DepartmentService {

    @Autowired
    private DepartmentBaseMapper departmentBaseMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentVo> getAllDepartmentTree(Long organizationId) throws Exception {
        return TreeUtils.listToTree(this.getAllDepartmentTreeExamine(organizationId));
    }

    @Override
    public List<Department> getAllDepartmentList(Long organizationId, String keyWords) {
        DepartmentParam departmentParam = new DepartmentParam();
        departmentParam.setIsDelete(YesNoEnum.NO.toString());
        departmentParam.setOrganizationId(organizationId);
        if (keyWords != null)
            departmentParam.setName(keyWords);
        departmentParam.setLikeMode(LikeModeEnum.ALL);
        return departmentBaseMapper.getAll(departmentParam);
    }

    @Override
    public List<DepartmentVo> getAllDepartmentTreeExamine(Long organizationId) {
        List<DepartmentVo> departmentVos = new ArrayList<DepartmentVo>();
        DepartmentParam departmentParam = new DepartmentParam();
        departmentParam.setIsDelete(YesNoEnum.NO.toString());
        departmentParam.setOrganizationId(organizationId);
        List<Department> departmentList = departmentBaseMapper.getAll(departmentParam);
        for (int i = 0; i < departmentList.size(); i++) {
            DepartmentVo departmentVo = new DepartmentVo();
            BeanUtils.copyProperties(departmentList.get(i), departmentVo);
            departmentVos.add(departmentVo);
        }
        return departmentVos;
    }

    @Override
    public List<Department> getExamineList(List<Long> deptIds) {
        return departmentMapper.getExamineList(deptIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Department updateDepartment(Department department) throws Exception{
        //检查组织默认值
        String checkValue = department.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        // 检测部门是否存在
        this.checkDepartment(department);
        departmentBaseMapper.updateByKey(department.getId(),department);
        return departmentBaseMapper.selectOneByKey(department.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Department addDepartment(Department department) throws Exception{
        this.checkDepartment(department);
        this.check(department);
        departmentBaseMapper.insert(department);
        return departmentBaseMapper.selectOneByKey(department.getId());
    }

    private void checkDepartment(Department department) throws Exception{
        DepartmentParam departmentParam = new DepartmentParam();
        departmentParam.setOrganizationId(department.getOrganizationId());
        departmentParam.setName(department.getName());
        Department newDepartment = departmentBaseMapper.selectOne(departmentParam);
        if(newDepartment != null){
            throw new CustomException(ResponseEnum.DEPARTMENT_EXIST_ERROR);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Department deleteDept(Long id) throws Exception {
        DepartmentParam departmentParam = new DepartmentParam();
        departmentParam.setIsDelete(YesNoEnum.NO.toString());
        departmentParam.setParentId(id);
        int count = this.count(departmentParam);
        if (count != 0) {
            throw new CustomException("无法删除!存在子部门");
        }
        return this.updateFieldByKey(id, "isDelete", YesNoEnum.YES);
    }

}
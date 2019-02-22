package com.cnuip.console.repository;


import com.cnuip.base.domain.console.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface DepartmentMapper
{
    List<Department> getExamineList(@Param("deptIds") List<Long> deptIds);
}
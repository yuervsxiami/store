package com.cnuip.colligate.service;

import com.cnuip.base.domain.console.Department;
import com.cnuip.base.domain.console.Post;
import com.cnuip.base.domain.console.Powers;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface ConsoleService {

    List<Department> getTreeExamine(Long organizationId, Long powersId) throws Exception;

    Department deleteDept(Long id) throws Exception;

    Powers deletePowers(Long id) throws Exception;

    Post deletePost(Long id) throws Exception;
}

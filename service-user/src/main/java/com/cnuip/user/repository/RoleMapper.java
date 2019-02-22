package com.cnuip.user.repository;

import com.cnuip.base.domain.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface RoleMapper
{
    List<User> searchUser(@Param("roleId") Long roleId);
}
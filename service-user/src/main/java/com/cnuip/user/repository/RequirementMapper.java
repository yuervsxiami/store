package com.cnuip.user.repository;


import com.cnuip.user.vo.RequirementCountVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface RequirementMapper
{
    RequirementCountVo countRequirement(@Param("userId") Long userId);
}
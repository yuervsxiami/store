package com.cnuip.user.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface VerifyCodeMapper
{
    Boolean compareTime(@Param("createdTime") Date createdTime);
}
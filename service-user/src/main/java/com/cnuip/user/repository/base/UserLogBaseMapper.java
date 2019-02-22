package com.cnuip.user.repository.base;

import com.cnuip.base.domain.params.UserLogParam;
import com.cnuip.base.domain.user.UserLog;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface UserLogBaseMapper extends AbstractMapper<UserLog, UserLogParam>
{}
package com.cnuip.base.service;


import com.cnuip.base.base.BaseModel;
import com.cnuip.base.web.BaseException;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface BaseService {
    <T extends BaseModel> T check(T t) throws BaseException;
}
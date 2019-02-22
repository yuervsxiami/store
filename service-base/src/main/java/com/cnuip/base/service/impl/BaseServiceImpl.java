package com.cnuip.base.service.impl;


import com.cnuip.base.base.BaseModel;
import com.cnuip.base.service.BaseService;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public class BaseServiceImpl implements BaseService {
    @Override
    public <T extends BaseModel> T check(T t) throws BaseException {
        t.setDefaultValue();
        String checkValue = t.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        return t;
    }
}

package com.cnuip.user.service;

import com.cnuip.base.domain.params.VerifyCodeParam;
import com.cnuip.base.domain.user.VerifyCode;
import com.cnuip.base.service.AbstractService;
import com.cnuip.user.rest.exceptions.CustomException;
import com.cnuip.user.vo.VerifyCodeActionEnum;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface VerifyCodeService extends AbstractService<VerifyCode, VerifyCodeParam> {
    /**
     * 发送验证码
     * @param phone
     * @return
     * @throws Exception
     */
    Boolean sendVerifyCode(String phone, VerifyCodeActionEnum verifyCodeAction) throws CustomException;

    /**
     * 校验验证码
     * @param phone
     * @param code
     * @param verifyCodeAction
     * @return
     */
    Boolean checkVerifyCode(String phone, String code, VerifyCodeActionEnum verifyCodeAction);


}

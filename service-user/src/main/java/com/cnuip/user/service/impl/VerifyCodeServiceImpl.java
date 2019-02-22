package com.cnuip.user.service.impl;

import com.cnuip.base.domain.params.UserParam;
import com.cnuip.base.domain.params.VerifyCodeParam;
import com.cnuip.base.domain.user.VerifyCode;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.utils.StringUtils;
import com.cnuip.user.repository.VerifyCodeMapper;
import com.cnuip.user.repository.base.UserBaseMapper;
import com.cnuip.user.repository.base.VerifyCodeBaseMapper;
import com.cnuip.user.rest.exceptions.CustomException;
import com.cnuip.user.rest.exceptions.ResponseEnum;
import com.cnuip.user.service.AliSmsService;
import com.cnuip.user.service.VerifyCodeService;
import com.cnuip.user.vo.VerifyCodeActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class VerifyCodeServiceImpl
        extends AbstractServiceImpl<VerifyCode, VerifyCodeParam>
        implements VerifyCodeService {

    @Autowired
    private VerifyCodeBaseMapper verifyCodeBaseMapper;

    @Autowired
    private VerifyCodeMapper verifyCodeMapper;

    @Autowired
    private UserBaseMapper userBaseMapper;

    @Autowired
    private AliSmsService aliSmsService;

    @Override
    public Boolean sendVerifyCode(String phone, VerifyCodeActionEnum verifyCodeAction) throws CustomException {
        /**
         * 验证手机号码
         */
        UserParam userParam = new UserParam();
        userParam.setUsername(phone);
        int count = userBaseMapper.count(userParam);
        if (verifyCodeAction == VerifyCodeActionEnum.LOGIN && count == 0) {
            throw new CustomException(ResponseEnum.PHONE_ISNOTEXIST);
        } else if (verifyCodeAction == VerifyCodeActionEnum.FORGET && count == 0) {
            throw new CustomException(ResponseEnum.PHONE_ISNOTEXIST);
        } else if (verifyCodeAction == VerifyCodeActionEnum.REGISTER && count != 0) {
            throw new CustomException(ResponseEnum.PHONE_ISEXIST);
        }

        /**
         * 生成并发送验证码
         */
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        String json = "{\"code\":\"%1s\"}";
        Boolean success = aliSmsService.sendSms(phone, "SMS_147435440", json, code);  /*SMS_130914789*/
        if (success) {
            /**
             * 保存验证码
             */
            VerifyCode verifyCode = new VerifyCode();
            verifyCode.setAction(verifyCodeAction.toString());
            verifyCode.setCode(code);
            verifyCode.setUsername(phone);
            verifyCodeBaseMapper.insert(verifyCode);
        }
        return success;
    }

    @Override
    public Boolean checkVerifyCode(String phone, String code, VerifyCodeActionEnum verifyCodeAction) {
        /**
         * 校验验证码是否存在
         */
        if (StringUtils.isNullOrEmpty(phone)){
            return false;
        }
        if (StringUtils.isNullOrEmpty(code)){
            return false;
        }
        VerifyCodeParam verifyCodeParams = new VerifyCodeParam();
        verifyCodeParams.setCode(code);
        verifyCodeParams.setUsername(phone);
        verifyCodeParams.setAction(verifyCodeAction.toString());
        VerifyCode verifyCode = verifyCodeBaseMapper.selectOne(verifyCodeParams);
        if (verifyCode == null) {
            return false;
        }

        /**
         * 判断是否为最新验证码
         */
        verifyCodeParams = new VerifyCodeParam();
        verifyCodeParams.setUsername(phone);
        verifyCodeParams.setAction(verifyCodeAction.toString());
        verifyCodeParams.setOrderBy("createdTime DESC");
        verifyCode = verifyCodeBaseMapper.selectOne(verifyCodeParams);
        if(verifyCode==null){
            return false;
        }
        if(!code.equals(verifyCode.getCode())){
            return false;
        }
        /**
         * 校验验证码是否失效
         */
       return verifyCodeMapper.compareTime(verifyCode.getCreatedTime());
    }
}
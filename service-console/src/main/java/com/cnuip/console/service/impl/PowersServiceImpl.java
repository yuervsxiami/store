package com.cnuip.console.service.impl;

import com.cnuip.base.domain.console.Powers;
import com.cnuip.base.domain.params.PowersParam;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.console.repository.PowersMapper;
import com.cnuip.console.repository.base.PowersBaseMapper;
import com.cnuip.console.rest.exceptions.CustomException;
import com.cnuip.console.rest.exceptions.ResponseEnum;
import com.cnuip.console.service.PowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class PowersServiceImpl extends AbstractServiceImpl<Powers, PowersParam> implements PowersService {

    @Autowired
    private PowersBaseMapper powersBaseMapper;

    @Autowired
    private PowersMapper powersMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Powers updatePowers(Powers powers) throws Exception{
        //检查岗位默认值
        String checkValue = powers.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        // 检测部门是否存在
        this.checkPowers(powers);
        powersBaseMapper.updateByKey(powers.getId(),powers);
        return powersBaseMapper.selectOneByKey(powers.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Powers addPowers(Powers powers) throws Exception{
        this.checkPowers(powers);
        this.check(powers);
        powersBaseMapper.insert(powers);
        return powersBaseMapper.selectOneByKey(powers.getId());
    }

    private void checkPowers(Powers powers) throws Exception{
        PowersParam powersParam = new PowersParam();
        powersParam.setOrganizationId(powers.getOrganizationId());
        powersParam.setName(powers.getName());
        Powers newPowers = powersBaseMapper.selectOne(powersParam);
        if(newPowers != null){
            throw new CustomException(ResponseEnum.POWERS_EXIST_ERROR);
        }
    }
}
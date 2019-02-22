package com.cnuip.colligate.service.impl;

import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.ClientServiceUtils;
import com.cnuip.colligate.client.PatentResultClient;
import com.cnuip.colligate.client.UserClient;
import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.service.PatentResultService;
import com.cnuip.colligate.vo.PatentResultVo;
import com.cnuip.gaea.service.web.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xjt on 2018/8/30.
 */
@Service
public class PatentResultServiceImpl implements PatentResultService {

    Logger logger = LoggerFactory.getLogger(PatentResultServiceImpl.class);

    @Autowired
    private UserClient userClient;

    @Autowired
    private PatentResultClient patentResultClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PatentResultVo addPatentResult(PatentResultVo patentResultVo) throws Exception{
        //查询操作人
        ApiResponse<User> userRes = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryDetail", patentResultVo.getEditorId());
        if (userRes.getCode() != 0) {
            throw new ClientException(userRes.getCode(),userRes.getMessage());
        }
        patentResultVo.setEditorName(userRes.getResult().getRealName());
        //新增科技成果
        ApiResponse<PatentResultVo> patentResultVoRes = new ClientServiceUtils<PatentResultVo, PatentResultClient>().exec(patentResultClient, "addPatentResult",patentResultVo);
        if (patentResultVoRes.getCode() != 0) {
            logger.error("RESULT CONNECT ERROR+++++++++++++++++++++++++++++++++");
            throw new ClientException(patentResultVoRes.getCode(),patentResultVoRes.getMessage());
        }
        patentResultVo = patentResultVoRes.getResult();
        return patentResultVo;
    }
}

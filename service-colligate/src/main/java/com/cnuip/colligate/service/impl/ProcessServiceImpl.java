package com.cnuip.colligate.service.impl;

import com.cnuip.base.domain.process.ProcessTaskUser;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.ClientServiceUtils;
import com.cnuip.colligate.client.ProcessClient;
import com.cnuip.colligate.client.UserClient;
import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.exception.enums.ClientEnum;
import com.cnuip.colligate.service.ProcessService;
import com.cnuip.colligate.vo.ProcessTaskVo;
import com.cnuip.colligate.vo.ProcessVo;
import com.cnuip.colligate.vo.TmplProcessTaskDepartmentVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessClient processClient;

    @Autowired
    private UserClient userClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessVo addProcess(Long editorId, Long orgId, ProcessVo processVo) throws Exception {
        // 新增提案
        //查询提案人
        ApiResponse<User> userRes = new ClientServiceUtils<User, UserClient>().exec(userClient, "queryDetail", editorId);
        if (userRes.getCode() != 0) {
            throw new ClientException(userRes.getCode(),userRes.getMessage());
        }
        processVo.setEditorId(editorId);
        processVo.setEditorName(userRes.getResult().getRealName());
        processVo.setOrganizationId(orgId);
        ApiResponse<ProcessVo> processVoRes = new ClientServiceUtils<ProcessVo, ProcessClient>().exec(processClient, "addProcess",processVo);
        if (processVoRes.getCode() != 0) {
            throw new ClientException(processVoRes.getCode(),processVoRes.getMessage());
        }
        processVo = processVoRes.getResult();
        //获取提案审核人
        List<ProcessTaskVo> ProcessTaskVoList = processVo.getProcessTaskVoList();
        for (ProcessTaskVo processTaskVo : ProcessTaskVoList) {
            long processTaskId = processTaskVo.getId();
            //职权ID
            long powersId = processTaskVo.getPowersId();
            for (TmplProcessTaskDepartmentVo tmplProcessTaskDepartmentVo : processTaskVo.getProcessTaskDepartmentVoList()) {
                //根据职权ID和部门ID查询审核人
                ApiResponse<PageInfo<User>> resUser = new ClientServiceUtils<PageInfo<User>, UserClient>().exec(userClient, "queryForBase", tmplProcessTaskDepartmentVo.getDepartmentId(), powersId);
                if (resUser.getCode() == 0) {
                    for (User user : resUser.getResult().getList()) {
                        if("admin".equals(user.getUsername())){
                            continue;
                        }
                        //新增提案环节审核人
                        ProcessTaskUser processTaskUser = new ProcessTaskUser();
                        processTaskUser.setProcessId(processVo.getId());
                        processTaskUser.setProcessTaskId(processTaskId);
                        processTaskUser.setDepartmentId(user.getDepartmentId());
                        processTaskUser.setExaminId(user.getId());
                        processTaskUser.setExaminName(user.getRealName());
                        ApiResponse<ProcessTaskUser> processTaskUserRes = new ClientServiceUtils<ProcessTaskUser, ProcessClient>().exec(processClient, "addProcessTaskUser", processTaskUser);
                        if (processTaskUserRes.getCode() != 0) {
                            throw new ClientException(processTaskUserRes.getCode(),processTaskUserRes.getMessage());
                        }
                    }
                } else {
                    throw new ClientException(ClientEnum.USER_ERROR);
                }
            }
        }
        return processVo;
    }
}
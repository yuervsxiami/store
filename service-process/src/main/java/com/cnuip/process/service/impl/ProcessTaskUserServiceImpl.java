package com.cnuip.process.service.impl;

import com.cnuip.base.domain.enums.ProcessStateEnum;
import com.cnuip.base.domain.enums.ProcessTypeEnum;
import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.enums.YesNoNoneEnum;
import com.cnuip.base.domain.params.ProcessTaskUserParam;
import com.cnuip.base.domain.process.Process;
import com.cnuip.base.domain.process.ProcessTask;
import com.cnuip.base.domain.process.ProcessTaskUser;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.process.exception.ProcessException;
import com.cnuip.process.exception.enums.ProcessEnum;
import com.cnuip.process.repository.ProcessTaskMapper;
import com.cnuip.process.repository.ProcessTaskUserMapper;
import com.cnuip.process.repository.base.ProcessBaseMapper;
import com.cnuip.process.repository.base.ProcessTaskBaseMapper;
import com.cnuip.process.repository.base.ProcessTaskUserBaseMapper;
import com.cnuip.process.repository.base.TmplProcessBaseMapper;
import com.cnuip.process.service.ProcessTaskUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class ProcessTaskUserServiceImpl extends AbstractServiceImpl<ProcessTaskUser, ProcessTaskUserParam> implements ProcessTaskUserService {

    @Autowired
    private ProcessTaskUserBaseMapper processTaskUserBaseMapper;

    @Autowired
    private ProcessBaseMapper processBaseMapper;

    @Autowired
    private TmplProcessBaseMapper tmplProcessBaseMapper;

    @Autowired
    private ProcessTaskBaseMapper processTaskBaseMapper;

    @Autowired
    private ProcessTaskMapper processTaskMapper;

    @Autowired
    private ProcessTaskUserMapper processTaskUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessTaskUser updateProcessTaskState(ProcessTaskUser processTaskUser) throws Exception{
        // 提案Id
        Long processId = processTaskUser.getProcessId();
        // 提案环节Id
        Long processTaskId = processTaskUser.getProcessTaskId();
        // 审核状态
        String isExamined = processTaskUser.getIsExamined();

        //查询所处的提案环节
        ProcessTask processTaskNow = processTaskBaseMapper.selectOneByKey(processTaskId);
        if(processTaskNow == null){
            throw new ProcessException(ProcessEnum.PROCESSTASK_NOT_EXIST);
        }
        if(YesNoEnum.YES.name().equals(processTaskNow.getIsFinished())){
            throw new ProcessException(ProcessEnum.PROCESSTASK_ISFINISH);
        }

        //查询修改的提案环节审核人
        ProcessTaskUserParam processTaskUserParam = new ProcessTaskUserParam();
        processTaskUserParam.setProcessId(processId);
        processTaskUserParam.setProcessTaskId(processTaskId);
        processTaskUserParam.setExaminId(processTaskUser.getExaminId());

        ProcessTaskUser processTaskUserChange = processTaskUserBaseMapper.selectOne(processTaskUserParam);
        if(processTaskUserChange == null){
            throw new ProcessException(ProcessEnum.PROCESSTASK_NOT_EXIST);
        }

        //修改提交的提案环节状态
        processTaskUserBaseMapper.update(processTaskUserParam,processTaskUser);

        //检查当前提案环节状态并修改
        //获取提案
        Process process = processBaseMapper.selectOneByKey(processId);
        if(process == null){
            throw new BaseException(ResponseBaseEnum.PROCESS_CONTENT_CANNOT_NULL);
        }
        //修改提案状态为审核中
        if(ProcessStateEnum.EXAMINING.name().equals(process.getState())){
            processBaseMapper.updateFieldByKey(process.getId(),"state",ProcessStateEnum.RUNNING.name());
        }

        //普签与或签
        if(ProcessTypeEnum.NORMAL.name().equals(process.getType()) || ProcessTypeEnum.OR.name().equals(process.getType())){
            processTaskBaseMapper.updateFieldByKey(processTaskId,"isFinished", YesNoEnum.YES.name());
            //修改提案状态
            if(YesNoNoneEnum.NO.name().equals(isExamined)){
                processBaseMapper.updateFieldByKey(process.getId(),"state",ProcessStateEnum.UNEXAMINED.name());
            }
            if(ProcessTypeEnum.OR.name().equals(process.getType())) {
                // 修改该环节下的其他审核人的审核状态
                ProcessTaskUser taskUser = new ProcessTaskUser();
                processTaskUserParam.setExaminId(null);
                taskUser.setIsExamined(isExamined);
                processTaskUserBaseMapper.update(processTaskUserParam,taskUser);
            }
            if(YesNoNoneEnum.YES.name().equals(isExamined)){
                // 查询是否有下一环节
                ProcessTask processTask = processTaskMapper.selectNextTask(processId,processTaskId);
                if(processTask == null){
                    processBaseMapper.updateFieldByKey(process.getId(),"state",ProcessStateEnum.FINISHED.name());
                }
            }
        }

        //查询该提案环节下的所有审核人
        ProcessTaskUserParam processTaskUserChangeParam = new ProcessTaskUserParam();
        processTaskUserChangeParam.setProcessId(processId);
        processTaskUserChangeParam.setProcessTaskId(processTaskId);
        List<ProcessTaskUser> processTaskUserList = processTaskUserBaseMapper.getAll(processTaskUserChangeParam);

        // 会签
        if(ProcessTypeEnum.AND.name().equals(process.getType())){
            if(YesNoNoneEnum.NO.name().equals(isExamined)){
                processTaskBaseMapper.updateFieldByKey(processTaskId,"isFinished", YesNoEnum.YES.name());
                processBaseMapper.updateFieldByKey(process.getId(),"state",ProcessStateEnum.UNEXAMINED.name());
                // 修改该环节下的其他审核人的审核状态
                ProcessTaskUser taskUser = new ProcessTaskUser();
                ProcessTaskUserParam taskUserParam = new ProcessTaskUserParam();
                taskUserParam.setProcessId(processId);
                taskUserParam.setProcessTaskId(processTaskId);
                taskUserParam.setIsExamined(YesNoNoneEnum.NONE.name());
                taskUser.setIsExamined(isExamined);
                processTaskUserBaseMapper.update(taskUserParam,taskUser);
            }
            if(YesNoNoneEnum.YES.name().equals(isExamined)){
                boolean isFinished = true;
                for(ProcessTaskUser taskUser:processTaskUserList){
                    if(YesNoNoneEnum.NO.name().equals(taskUser.getIsExamined()) || YesNoNoneEnum.NONE.name().equals(taskUser.getIsExamined())){
                        isFinished = false;
                        break;
                    }
                }
                if(isFinished){
                    processTaskBaseMapper.updateFieldByKey(processTaskId,"isFinished", YesNoEnum.YES.name());
                    ProcessTask processTask = processTaskMapper.selectNextTask(processId,processTaskId);
                    if(processTask == null){
                        processBaseMapper.updateFieldByKey(process.getId(),"state",ProcessStateEnum.FINISHED.name());
                    }
                }
            }
        }

        return processTaskUser;
    }
}
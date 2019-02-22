package com.cnuip.process.service.impl;


import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.*;
import com.cnuip.base.domain.process.Process;
import com.cnuip.base.domain.process.*;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.utils.UUidUtils;
import com.cnuip.process.exception.ProcessException;
import com.cnuip.process.exception.enums.ProcessEnum;
import com.cnuip.process.repository.ProcessMapper;
import com.cnuip.process.repository.ProcessTaskMapper;
import com.cnuip.process.repository.base.ProcessBaseMapper;
import com.cnuip.process.repository.base.ProcessPersonBaseMapper;
import com.cnuip.process.repository.base.TmplProcessPersonBaseMapper;
import com.cnuip.process.service.*;
import com.cnuip.process.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class ProcessServiceImpl extends AbstractServiceImpl<Process, ProcessParam> implements ProcessService {

    @Autowired
    private ProcessBaseMapper processBaseMapper;

    @Autowired
    private TmplProcessService tmplProcessService;

    @Autowired
    private ProcessTaskService processTaskService;

    @Autowired
    private TmplProcessTaskDepartmentService tmplProcessTaskDepartmentService;

    @Autowired
    private ProcessTaskUserService processTaskUserService;

    @Autowired
    private ProcessRequisitionService processRequisitionService;

    @Autowired
    private ProcessAttachmentService processAttachmentService;

    @Autowired
    private TmplProcessPersonBaseMapper tmplProcessPersonBaseMapper;

    @Autowired
    private ProcessPersonBaseMapper processPersonBaseMapper;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ProcessTaskMapper processTaskMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessVo addProcess(ProcessVo processVo) throws Exception{
        //检查提案编号
        ProcessParam processParam = new ProcessParam();
        processParam.setOrganizationId(processVo.getOrganizationId());
        processParam.setNo(processVo.getNo());
        Process process = processBaseMapper.selectOne(processParam);
        if(process != null){
            throw new ProcessException(ProcessEnum.PROCESS_NO_EXIST);
        }

        //查询流程模板详情
        TmplProcessVo tmplProcessVo = tmplProcessService.selectTmplProcessDetail(processVo.getTmplProcessId());

        if(tmplProcessVo == null){
            throw new ProcessException(ProcessEnum.TMPLPROCESS_NULL);
        }

        processVo.setType(tmplProcessVo.getType());
        //新增提案
        this.check(processVo);
        processBaseMapper.insert(processVo);

        if(processVo.getProcessAttachmentList() != null){
            //新增提案附件
            for(ProcessAttachment processAttachment:processVo.getProcessAttachmentList()){
                processAttachment.setProcessId(processVo.getId());
                processAttachmentService.check(processAttachment);
                processAttachmentService.insert(processAttachment);
            }
        }

        //新增提案环节
        if(tmplProcessVo.getTmplProcessTaskList().size() > 0){
            for(TmplProcessTaskVo tmplProcessTaskVo:tmplProcessVo.getTmplProcessTaskList()){
                //新增提案环节
                ProcessTask processTask = new ProcessTask();
                processTask.setProcessId(processVo.getId());
                processTask.setTmplProcessTaskId(tmplProcessTaskVo.getId());
                processTask.setTmplProcessTaskNo((long)tmplProcessTaskVo.getNo());
                processTask.setName(tmplProcessTaskVo.getName());
                processTask.setPowersId(tmplProcessTaskVo.getPowersId());
                processTask.setPowersName(tmplProcessTaskVo.getPowersName());
                processTask.setIsFinished(YesNoEnum.NO.name());
                processTaskService.check(processTask);
                processTaskService.insert(processTask);
            }
        }else{
            throw new ProcessException(ProcessEnum.TMPLPROCESS_OR_TMPLPROCESSTASK_NULL);
        }

        //查询流程模板的抄送人
        TmplProcessPersonParam tmplProcessPersonParam = new TmplProcessPersonParam();
        tmplProcessPersonParam.setTmplProcessId(tmplProcessVo.getId());
        List<TmplProcessPerson> tmplProcessPersonList = tmplProcessPersonBaseMapper.getAll(tmplProcessPersonParam);
        // 新增提案抄送人
        for(TmplProcessPerson tmplProcessPerson:tmplProcessPersonList){
            ProcessPerson processPerson = new ProcessPerson();
            processPerson.setProcessId(processVo.getId());
            processPerson.setPersonId(tmplProcessPerson.getPersonId());
            processPerson.setPersonName(tmplProcessPerson.getPersonName());

            processPersonBaseMapper.insert(processPerson);
        }
        return selectProcessDetail(processVo.getId());
    }

    @Override
    public ProcessVo selectProcessDetail(Long processId) throws Exception{
        ProcessVo processVo = new ProcessVo();
        Process process = processBaseMapper.selectOneByKey(processId);
        if(process == null){
            throw new ProcessException(ProcessEnum.PROCESS_NULL);
        }
        BeanUtils.copyProperties(process,processVo);
        //查询提案附件
        ProcessAttachmentParam processAttachmentParam = new ProcessAttachmentParam();
        processAttachmentParam.setProcessId(processId);
        List<ProcessAttachment> processAttachmentList = processAttachmentService.getAll(processAttachmentParam);
        processVo.setProcessAttachmentList(processAttachmentList);
        //查询提案申请书
        ProcessRequisitionParam processRequisitionParam = new ProcessRequisitionParam();
        processRequisitionParam.setProcessId(processId);
        List<ProcessRequisition> processRequisitionList = processRequisitionService.getAll(processRequisitionParam);
        processVo.setProcessRequisitionList(processRequisitionList);
        //查询提案环节
        ProcessTaskParam processTaskParam = new ProcessTaskParam();
        processTaskParam.setProcessId(processId);
        List<ProcessTask> processTaskList = processTaskService.getAll(processTaskParam);
        List<ProcessTaskVo> processTaskVoList = new ArrayList<>();
        //查询提案环节审核人
        for(ProcessTask processTask:processTaskList){
            ProcessTaskVo processTaskVo = new ProcessTaskVo();
            if(processTask != null) {
                BeanUtils.copyProperties(processTask,processTaskVo);
                Long processTaskId = processTask.getId();
                // 查询提案环节下有哪些部门
                TmplProcessTaskDepartmentParam tmplProcessTaskDepartmentParam = new TmplProcessTaskDepartmentParam();
                tmplProcessTaskDepartmentParam.setTmplProcessTaskId(processTask.getTmplProcessTaskId());
                List<TmplProcessTaskDepartment> tmplProcessTaskDepartmentList = tmplProcessTaskDepartmentService.getAll(tmplProcessTaskDepartmentParam);
                List<TmplProcessTaskDepartmentVo> tmplProcessTaskDepartmentVoList = new ArrayList<>();
                for (TmplProcessTaskDepartment tmplProcessTaskDepartment : tmplProcessTaskDepartmentList) {
                    TmplProcessTaskDepartmentVo tmplProcessTaskDepartmentVo = new TmplProcessTaskDepartmentVo();
                    BeanUtils.copyProperties(tmplProcessTaskDepartment,tmplProcessTaskDepartmentVo);
                    //根据部门ID、提案环节ID 查找审核人
                    ProcessTaskUserParam processTaskUserParam = new ProcessTaskUserParam();
                    processTaskUserParam.setDepartmentId(tmplProcessTaskDepartment.getDepartmentId());
                    processTaskUserParam.setProcessTaskId(processTaskId);
                    processTaskUserParam.setProcessId(processId);
                    List<ProcessTaskUser> processTaskUserList = processTaskUserService.getAll(processTaskUserParam);

                    //部门详情赋值
                    tmplProcessTaskDepartmentVo.setProcessTaskUserList(processTaskUserList);
                    tmplProcessTaskDepartmentVoList.add(tmplProcessTaskDepartmentVo);
                }
                processTaskVo.setProcessTaskDepartmentVoList(tmplProcessTaskDepartmentVoList);
                processTaskVoList.add(processTaskVo);
            }
        }
        processVo.setProcessTaskVoList(processTaskVoList);

        //查询提案抄送人
        ProcessPersonParam processPersonParam = new ProcessPersonParam();
        processPersonParam.setProcessId(processId);
        List<ProcessPerson> processPersonList = processPersonBaseMapper.getAll(processPersonParam);
        processVo.setPersonList(processPersonList);

        return processVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessVo deleteProcess(Long processId) throws Exception{
        ProcessVo processVo = selectProcessDetail(processId);
        //删除提案
        processBaseMapper.deleteByKey(processId);
        //删除提案附件
        processAttachmentService.deleteByField("process_id",processId,null);
        //删除提案环节
        processTaskService.deleteByField("process_id",processId,null);
        //删除提案申请书
        processRequisitionService.deleteByField("process_id",processId,null);
        //删除提案审核人
        processTaskUserService.deleteByField("process_id",processId,null);
        //删除提案抄送人
        processPersonBaseMapper.deleteByField("process_id",processId,null);

        return processVo;
    }

    @Override
    public PageInfo<ProcessListVo> selectProcess(Long userId, String username, ProcessParam processParam) throws Exception{
        if (processParam.getPageNum() == null) {
            processParam.setPageNum(1);
        }
        if (processParam.getPageSize() == null) {
            processParam.setPageSize(10);
        }
        PageInfo<ProcessListVo> processList = new PageInfo<>();
        if("admin".equals(username)){
            PageInfo<Process> newProcessList = this.selectMany(processParam);
            BeanUtils.copyProperties(newProcessList,processList);
        }else{
            //查询所有需要该用户审核的提案列表
            processList = ((Page<ProcessListVo>)(processMapper.selectProcessForEditor(processParam,userId,processParam.getPageNum(),processParam.getPageSize()))).toPageInfo();
        }
        return processList;
    }

    @Override
    public PageInfo<Process> selectEditorProcess(Long userId, String username, ProcessParam processParam) throws Exception{
        if (processParam.getPageNum() == null) {
            processParam.setPageNum(1);
        }
        if (processParam.getPageSize() == null) {
            processParam.setPageSize(10);
        }
        PageInfo<Process> processList;
        if(!("admin".equals(username))){
            //查询该用户操作提案列表
            processParam.setEditorId(userId);
        }
        processList = this.selectMany(processParam);
        return processList;
    }


    @Override
    public List<User> selectProcessUser(Long orgId) throws Exception {
        return processMapper.selectProcessUser(orgId);
    }

    @Override
    public PageInfo<Process> selectCopyProcess(Long userId, ProcessParam processParam) throws Exception {
        Page<Process> processes = (Page<Process>)(processMapper.selectCopyProcess(processParam,userId,processParam.getPageNum(),processParam.getPageSize()));
        return processes.toPageInfo();
    }

    @Override
    public ProcessNumVo searchProcessNum(Long userId,Long orgId) throws Exception {
        ProcessParam processParam = new ProcessParam();
        processParam.setPageNum(1);
        processParam.setPageSize(1);
        processParam.setOrganizationId(orgId);
        // 查询由我审核的提案数
        Long auditNum = this.selectProcess(userId,null,processParam).getTotal();
        // 查询抄送给我的提案数
        Long copyNum = this.selectCopyProcess(userId,processParam).getTotal();
        // 查询由我发起的提案数
        Long porcessNum = this.selectEditorProcess(userId,null,processParam).getTotal();
        ProcessNumVo processNumVo = new ProcessNumVo();
        processNumVo.setProcessNum(porcessNum);
        processNumVo.setAuditNum(auditNum);
        processNumVo.setCopyNum(copyNum);
        return processNumVo;
    }

    @Override
    public ProcessAppVo selectAppProcess(Long userId, String username, ProcessParam processParam) throws Exception {
        // 获取列表
        PageInfo<Process> processPageInfo = this.selectEditorProcess(userId,username,processParam);
        // 获取统计数据
        ProcessNumVo processNumVo = this.searchProcessNum(userId,processParam.getOrganizationId());

        ProcessAppVo processAppVo = new ProcessAppVo();
        processAppVo.setProcessList(processPageInfo);
        processAppVo.setProcessNum(processNumVo.getProcessNum());
        processAppVo.setAuditNum(processNumVo.getAuditNum());
        processAppVo.setCopyNum(processNumVo.getCopyNum());
        return processAppVo;
    }

    @Override
    public ProcessAppAuditVo searchAppAudit(Long userId, String username, ProcessParam processParam) throws Exception {
        // 获取列表
        PageInfo<ProcessListVo> processPageInfo = this.selectProcess(userId,username,processParam);
        // 获取统计数据
        ProcessNumVo processNumVo = this.searchProcessNum(userId,processParam.getOrganizationId());

        ProcessAppAuditVo processAppVo = new ProcessAppAuditVo();
        processAppVo.setProcessList(processPageInfo);
        processAppVo.setProcessNum(processNumVo.getProcessNum());
        processAppVo.setAuditNum(processNumVo.getAuditNum());
        processAppVo.setCopyNum(processNumVo.getCopyNum());
        return processAppVo;
    }

    @Override
    public ProcessAppVo searchAppCopy(Long userId, ProcessParam processParam) throws Exception {
        // 获取列表
        PageInfo<Process> processPageInfo = this.selectCopyProcess(userId,processParam);
        // 获取统计数据
        ProcessNumVo processNumVo = this.searchProcessNum(userId,processParam.getOrganizationId());

        ProcessAppVo processAppVo = new ProcessAppVo();
        processAppVo.setProcessList(processPageInfo);
        processAppVo.setProcessNum(processNumVo.getProcessNum());
        processAppVo.setAuditNum(processNumVo.getAuditNum());
        processAppVo.setCopyNum(processNumVo.getCopyNum());
        return processAppVo;
    }

    @Override
    public String getProcessNum() throws Exception {
        return "TA"+ UUidUtils.getUUId(3);
    }
}
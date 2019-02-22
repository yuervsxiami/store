package com.cnuip.process.service.impl;

import com.cnuip.base.domain.params.TmplProcessTaskDepartmentParam;
import com.cnuip.base.domain.params.TmplProcessTaskParam;
import com.cnuip.base.domain.process.ProcessTask;
import com.cnuip.base.domain.process.TmplProcessTask;
import com.cnuip.base.domain.process.TmplProcessTaskDepartment;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.process.exception.ProcessException;
import com.cnuip.process.exception.enums.ProcessEnum;
import com.cnuip.process.repository.TmplProcessTaskMapper;
import com.cnuip.process.repository.base.TmplProcessTaskBaseMapper;
import com.cnuip.process.service.ProcessTaskService;
import com.cnuip.process.service.TmplProcessTaskDepartmentService;
import com.cnuip.process.service.TmplProcessTaskService;
import com.cnuip.process.vo.TmplProcessTaskDepartmentVo;
import com.cnuip.process.vo.TmplProcessTaskVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TmplProcessTaskServiceImpl extends AbstractServiceImpl<TmplProcessTask, TmplProcessTaskParam> implements TmplProcessTaskService {

    private Logger logger = LoggerFactory.getLogger(TmplProcessTaskServiceImpl.class);

    @Autowired
    private TmplProcessTaskBaseMapper tmplProcessTaskBaseMapper;

    @Autowired
    private TmplProcessTaskDepartmentService tmplProcessTaskDepartmentService;


    @Autowired
    private ProcessTaskService processTaskService;


    @Autowired
    private TmplProcessTaskMapper tmplProcessTaskMapper;

    @Override
    public List<TmplProcessTaskVo> selectTmplProcessTaskDetail(Long tmplProcessId) throws Exception{
        List<TmplProcessTaskVo> tmplProcessTaskVoList = new ArrayList<TmplProcessTaskVo>();
        TmplProcessTaskParam tmplProcessTaskParam = new TmplProcessTaskParam();
        tmplProcessTaskParam.setTmplProcessId(tmplProcessId);
        List<TmplProcessTask> tmplProcessTaskList = tmplProcessTaskBaseMapper.getAll(tmplProcessTaskParam);
        for(TmplProcessTask tmplProcessTask:tmplProcessTaskList){
            TmplProcessTaskVo tmplProcessTaskVo = new TmplProcessTaskVo();
            BeanUtils.copyProperties(tmplProcessTask,tmplProcessTaskVo);
            //查询流程环节模板对应部门详情
            List<TmplProcessTaskDepartment> tmplprocessTaskDepartmentList = tmplProcessTaskDepartmentService.selectTmplProcessTaskDepartmentDetail(tmplProcessTaskVo.getId());
            List<TmplProcessTaskDepartmentVo> tmplProcessTaskDepartmentVoList = new ArrayList<>();
            for(TmplProcessTaskDepartment tmplProcessTaskDepartment:tmplprocessTaskDepartmentList){
                TmplProcessTaskDepartmentVo tmplProcessTaskDepartmentVo = new TmplProcessTaskDepartmentVo();
                BeanUtils.copyProperties(tmplProcessTaskDepartment,tmplProcessTaskDepartmentVo);
                tmplProcessTaskDepartmentVoList.add(tmplProcessTaskDepartmentVo);
            }
            tmplProcessTaskVo.setTmplProcessTaskDepartmentVoList(tmplProcessTaskDepartmentVoList);
            tmplProcessTaskVoList.add(tmplProcessTaskVo);
        }
        return tmplProcessTaskVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TmplProcessTaskVo addTmplProcessTask(TmplProcessTaskVo tmplProcessTaskVo) throws Exception{
        try {
            //新增流程环节模板
            this.check(tmplProcessTaskVo);
            tmplProcessTaskBaseMapper.insert(tmplProcessTaskVo);

            // 删除现有的流程环节对应部门
            tmplProcessTaskDepartmentService.deleteByField("tmpl_process_task_id", tmplProcessTaskVo.getId(), null);
            List<TmplProcessTaskDepartmentVo> tmplProcessTaskDepartmentVoList = tmplProcessTaskVo.getTmplProcessTaskDepartmentVoList();
            for (TmplProcessTaskDepartmentVo tmplProcessTaskDepartmentVo : tmplProcessTaskDepartmentVoList) {
                //新增流程环节对应部门
                tmplProcessTaskDepartmentVo.setTmplProcessTaskId(tmplProcessTaskVo.getId());
                tmplProcessTaskDepartmentService.check(tmplProcessTaskDepartmentVo);
                tmplProcessTaskDepartmentService.insert(tmplProcessTaskDepartmentVo);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new ProcessException(ProcessEnum.INSERT_TMPLPROCESSTASK_ERROR,e);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TmplProcessTaskVo updateTmplProcessTask(TmplProcessTaskVo tmplProcessTaskVo) throws Exception{
        //修改流程环节模板
        tmplProcessTaskBaseMapper.updateByKey(tmplProcessTaskVo.getId(),tmplProcessTaskVo);
        List<TmplProcessTaskDepartmentVo> tmplProcessTaskDepartmentVoList = tmplProcessTaskVo.getTmplProcessTaskDepartmentVoList();
        if(tmplProcessTaskDepartmentVoList != null && tmplProcessTaskDepartmentVoList.size() > 0){
            for(TmplProcessTaskDepartmentVo tmplProcessTaskDepartmentVo:tmplProcessTaskDepartmentVoList){
                if(tmplProcessTaskDepartmentVo.getId() == null){
                    //新增流程环节对应部门
                    tmplProcessTaskDepartmentVo.setTmplProcessTaskId(tmplProcessTaskVo.getId());
                    tmplProcessTaskDepartmentService.check(tmplProcessTaskDepartmentVo);
                    tmplProcessTaskDepartmentService.insert(tmplProcessTaskDepartmentVo);
                }else{
                    //修改现有的流程环节对应部门
                    tmplProcessTaskDepartmentService.updateByKey(tmplProcessTaskDepartmentVo.getId(),tmplProcessTaskDepartmentVo);
                }
            }
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TmplProcessTaskVo deleteTmplProcessTask(Long tmplProcessTaskId) throws Exception{
        //查询流程环节模板是否在被使用
        ProcessTask processTask = processTaskService.selectOneByField("tmpl_process_task_id",tmplProcessTaskId,null);
        if(processTask != null){
            throw new ProcessException(ProcessEnum.TMPLPROCESSTASK_USED);
        }
        TmplProcessTaskVo tmplProcessTaskVo = new TmplProcessTaskVo();
        TmplProcessTask tmplProcessTask = tmplProcessTaskBaseMapper.selectOneByKey(tmplProcessTaskId);
        if(tmplProcessTask != null){
            BeanUtils.copyProperties(tmplProcessTask,tmplProcessTaskVo);
            //删除流程环节模板
            tmplProcessTaskBaseMapper.deleteByKey(tmplProcessTaskId);
        }
        TmplProcessTaskDepartmentParam tmplProcessTaskDepartmentParam = new TmplProcessTaskDepartmentParam();
        tmplProcessTaskDepartmentParam.setTmplProcessTaskId(tmplProcessTaskId);
        List<TmplProcessTaskDepartment> tmplProcessTaskDepartmentList = tmplProcessTaskDepartmentService.getAll(tmplProcessTaskDepartmentParam);
        if(tmplProcessTaskDepartmentList != null && tmplProcessTaskDepartmentList.size() > 0 ){
            tmplProcessTaskDepartmentService.delete(tmplProcessTaskDepartmentParam);
        }

        return tmplProcessTaskVo;
    }
}
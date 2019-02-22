package com.cnuip.process.service.impl;

import com.cnuip.base.domain.params.TmplProcessParam;
import com.cnuip.base.domain.params.TmplProcessPersonParam;
import com.cnuip.base.domain.process.TmplProcess;
import com.cnuip.base.domain.process.TmplProcessPerson;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.process.exception.ProcessException;
import com.cnuip.process.exception.enums.ProcessEnum;
import com.cnuip.process.repository.TmplProcessMapper;
import com.cnuip.process.repository.base.TmplProcessBaseMapper;
import com.cnuip.process.service.TmplProcessPersonService;
import com.cnuip.process.service.TmplProcessService;
import com.cnuip.process.service.TmplProcessTaskDepartmentService;
import com.cnuip.process.service.TmplProcessTaskService;
import com.cnuip.process.vo.TmplProcessTaskVo;
import com.cnuip.process.vo.TmplProcessVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Service
public class TmplProcessServiceImpl extends AbstractServiceImpl<TmplProcess, TmplProcessParam> implements TmplProcessService {

    @Autowired
    private TmplProcessBaseMapper tmplProcessBaseMapper;

    @Autowired
    private TmplProcessTaskService tmplProcessTaskService;

    @Autowired
    private TmplProcessTaskDepartmentService tmplProcessTaskDepartmentService;

    @Autowired
    private TmplProcessPersonService tmplProcessPersonService;

    @Autowired
    private TmplProcessMapper tmplProcessMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TmplProcessVo addTmplProcess(TmplProcessVo tmplProcessVo) throws Exception{
        //新增流程模板
        this.check(tmplProcessVo);
        tmplProcessBaseMapper.insert(tmplProcessVo);

        //新增流程模板抄送人
        for(TmplProcessPerson tmplProcessPerson:tmplProcessVo.getTmplProcessPersonList()){
            tmplProcessPerson.setTmplProcessId(tmplProcessVo.getId());
            tmplProcessPersonService.check(tmplProcessPerson);
            tmplProcessPersonService.insert(tmplProcessPerson);
        }

        List<TmplProcessTaskVo> tmplprocessTaskVoList = tmplProcessVo.getTmplProcessTaskList();
        for(TmplProcessTaskVo tmplProcessTaskVo:tmplprocessTaskVoList){
            //新增流程环节
            tmplProcessTaskVo.setTmplProcessId(tmplProcessVo.getId());
            tmplProcessTaskService.addTmplProcessTask(tmplProcessTaskVo);

        }

        return tmplProcessVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TmplProcessVo updateTmplProcess(TmplProcessVo tmplProcessVo) throws Exception{
        //检查流程模板默认值
        String checkValue = tmplProcessVo.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        //修改流程模板
        tmplProcessBaseMapper.updateByKey(tmplProcessVo.getId(),tmplProcessVo);

        if(tmplProcessVo.getTmplProcessPersonList() != null && tmplProcessVo.getTmplProcessPersonList().size() > 0){
            //删除原有流程模板抄送人
            tmplProcessPersonService.deleteByField("tmpl_process_id",tmplProcessVo.getId(),null);
            //新增流程模板抄送人
            for(TmplProcessPerson tmplProcessPerson:tmplProcessVo.getTmplProcessPersonList()){
                tmplProcessPerson.setTmplProcessId(tmplProcessVo.getId());
                tmplProcessPersonService.check(tmplProcessPerson);
                tmplProcessPersonService.insert(tmplProcessPerson);
            }
        }

        //修改流程环节模板
        List<TmplProcessTaskVo> tmplprocessTaskVoList = tmplProcessVo.getTmplProcessTaskList();
        //删除原有的流程环节模板
        tmplProcessTaskService.deleteByField("tmpl_process_id",tmplProcessVo.getId(),null);
        //新增流程环节模板
        if(tmplprocessTaskVoList != null && tmplprocessTaskVoList.size() > 0){
            for(TmplProcessTaskVo tmplProcessTaskVo:tmplprocessTaskVoList){
                tmplProcessTaskVo.setTmplProcessId(tmplProcessVo.getId());
                tmplProcessTaskService.addTmplProcessTask(tmplProcessTaskVo);
            }
        }
        return tmplProcessVo;
    }

    @Override
    public TmplProcessVo selectTmplProcessDetail(Long tmplProcessId) throws Exception{

        TmplProcessVo tmplProcessVo = new TmplProcessVo();
        TmplProcess tmplProcess = tmplProcessBaseMapper.selectOneByKey(tmplProcessId);
        if(tmplProcess == null){
            throw new ProcessException(ProcessEnum.TMPLPROCESS_NULL);
        }
        BeanUtils.copyProperties(tmplProcess,tmplProcessVo);
        //查询流程环节模板信息
        List<TmplProcessTaskVo> tmplProcessTaskList= tmplProcessTaskService.selectTmplProcessTaskDetail(tmplProcessId);
        tmplProcessVo.setTmplProcessTaskList(tmplProcessTaskList);
        //查询流程模板抄送人
        TmplProcessPersonParam tmplProcessPersonParam = new TmplProcessPersonParam();
        tmplProcessPersonParam.setTmplProcessId(tmplProcessId);
        List<TmplProcessPerson> tmplProcessPersonList = tmplProcessPersonService.getAll(tmplProcessPersonParam);
        tmplProcessVo.setTmplProcessPersonList(tmplProcessPersonList);

        return tmplProcessVo;
    }
}
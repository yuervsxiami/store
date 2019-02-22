package com.cnuip.result.service.impl;

import com.cnuip.base.domain.enums.PatentResultMaturityEnum;
import com.cnuip.base.domain.params.PatentResultAttachmentParam;
import com.cnuip.base.domain.params.PatentResultImageParam;
import com.cnuip.base.domain.params.PatentResultLabelParam;
import com.cnuip.base.domain.params.PatentResultParam;
import com.cnuip.base.domain.result.*;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.utils.UUidUtils;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.result.exception.ResultException;
import com.cnuip.result.exception.enums.ResultEnum;
import com.cnuip.result.repository.PatentResultMapper;
import com.cnuip.result.repository.base.PatentResultBaseMapper;
import com.cnuip.result.service.*;
import com.cnuip.result.vo.PatentResultVo;
import com.cnuip.result.vo.ResultAppVo;
import com.cnuip.result.vo.ResultNumVo;
import com.cnuip.result.vo.ResultWebNumVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xjt on 2018/8/30.
 */
@Service
public class PatentResultServiceImpl extends AbstractServiceImpl<PatentResult, PatentResultParam> implements PatentResultService {

    @Autowired
    private PatentResultBaseMapper patentResultBaseMapper;

    @Autowired
    private PatentResultAttachmentService patentResultAttachmentService;

    @Autowired
    private PatentResultImageService patentResultImageService;

    @Autowired
    private PatentResultLabelService patentResultLabelService;

    @Autowired
    private LabelValueService labelValueService;

    @Autowired
    private PatentResultMapper patentResultMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PatentResultVo addPatentResult(PatentResultVo patentResultVo) throws Exception{
        //添加专利成果
        this.check(patentResultVo);
        //检查科技成果编号是否存在
        //PatentResult patentResult = patentResultBaseMapper.selectOneByField("no",patentResultVo.getNo(),null);
        PatentResultParam patentResultParam = new PatentResultParam();
        patentResultParam.setOrganizationId(patentResultVo.getOrganizationId());
        patentResultParam.setNo(patentResultVo.getNo());
        PatentResult patentResult = patentResultBaseMapper.selectOne(patentResultParam);
        if(patentResult != null){
            throw new ResultException(ResultEnum.PATENTRESULT_EXIST);
        }
        patentResultBaseMapper.insert(patentResultVo);
        addPatentResultVo(patentResultVo);
        return selectPatentResultDetail(patentResultVo.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PatentResultVo updatePatentResult(PatentResultVo patentResultVo) throws Exception{
        //检查专利成果默认值
        String checkValue = patentResultVo.checkValue();
        if (checkValue != null) {
            throw new BaseException(ResponseBaseEnum.valueOf(checkValue));
        }
        PatentResultParam patentResultParam = new PatentResultParam();
        patentResultParam.setOrganizationId(patentResultVo.getOrganizationId());
        patentResultParam.setNo(patentResultVo.getNo());
        PatentResult patentResult = patentResultBaseMapper.selectOne(patentResultParam);
        if(patentResult != null){
            throw new ResultException(ResultEnum.PATENTRESULT_EXIST);
        }
        //修改专利成果
        patentResultBaseMapper.updateByKey(patentResultVo.getId(), patentResultVo);
        addPatentResultVo(patentResultVo);
        return selectPatentResultDetail(patentResultVo.getId());
    }

    @Override
    public PatentResultVo selectPatentResultDetail(Long patentResultId) throws Exception{
        PatentResultVo patentResultVo = new PatentResultVo();
        //查询专利成果
        PatentResult patentResult = patentResultBaseMapper.selectOneByKey(patentResultId);
        if(patentResult == null){
            return null;
        }
        BeanUtils.copyProperties(patentResult,patentResultVo);

        //查询专利成果附件
        PatentResultAttachmentParam patentResultAttachmentParam = new PatentResultAttachmentParam();
        patentResultAttachmentParam.setPatentResultId(patentResultId);
        List<PatentResultAttachment> patentResultAttachmentList = patentResultAttachmentService.getAll(patentResultAttachmentParam);
        patentResultVo.setPatentResultAttachmentList(patentResultAttachmentList);

        //查询专利成果图片
        PatentResultImageParam patentResultImageParam = new PatentResultImageParam();
        patentResultImageParam.setPatentResultId(patentResultId);
        List<PatentResultImage> patentResultImageList = patentResultImageService.getAll(patentResultImageParam);
        patentResultVo.setPatentResultImageList(patentResultImageList);

        //查询专利成果标签值
        List<LabelValue> labelValueList = new ArrayList<>();
        PatentResultLabelParam patentResultLabelParam = new PatentResultLabelParam();
        patentResultLabelParam.setPatentResultId(patentResultId);
        List<PatentResultLabel> patentResultLabelList = patentResultLabelService.getAll(patentResultLabelParam);
        for(PatentResultLabel patentResultLabel:patentResultLabelList){
            LabelValue labelValue = labelValueService.selectOneByKey(patentResultLabel.getLabelValueId());
            labelValueList.add(labelValue);
        }
        patentResultVo.setLabelValueList(labelValueList);

        return patentResultVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PatentResultVo deletePatentResult(long patentResultId) throws Exception{
        //删除专利成果
        patentResultBaseMapper.deleteByKey(patentResultId);
        //删除专利成果附件
        PatentResultAttachmentParam patentResultAttachmentParam = new PatentResultAttachmentParam();
        patentResultAttachmentParam.setPatentResultId(patentResultId);
        patentResultAttachmentService.delete(patentResultAttachmentParam);
        //删除专利成果图片
        PatentResultImageParam patentResultImageParam = new PatentResultImageParam();
        patentResultImageParam.setPatentResultId(patentResultId);
        patentResultImageService.delete(patentResultImageParam);
        //删除专利成果对应标签表
        PatentResultLabelParam patentResultLabelParam = new PatentResultLabelParam();
        patentResultLabelParam.setPatentResultId(patentResultId);
        patentResultLabelService.delete(patentResultLabelParam);

        return null;
    }

    @Override
    public ResultNumVo searchResultNum(Long orgId, Long userId, String username) throws Exception{
        PatentResultParam patentResultParam = new PatentResultParam();
        patentResultParam.setPageNum(1);
        patentResultParam.setPageSize(1);
        patentResultParam.setOrganizationId(orgId);
        if(!("admin".equals(username))){
            patentResultParam.setEditorId(userId);
        }
        // 获取通过小试的专利成果数
        patentResultParam.setMaturity(PatentResultMaturityEnum.SMALL_TEST.name());
        Long smallNum = this.selectMany(patentResultParam).getTotal();
        // 获取通过中试的专利成果数
        patentResultParam.setMaturity(PatentResultMaturityEnum.PILOT_TEST.name());
        Long pilotNum = this.selectMany(patentResultParam).getTotal();
        // 获取可以量产的专利成果数
        patentResultParam.setMaturity(PatentResultMaturityEnum.BATCH_PRODUCTION.name());
        Long batchNum = this.selectMany(patentResultParam).getTotal();
        // 获取已有样品的专利成果数
        patentResultParam.setMaturity(PatentResultMaturityEnum.SAMPLE.name());
        Long sampleNum = this.selectMany(patentResultParam).getTotal();

        ResultNumVo resultNumVo = new ResultNumVo();
        resultNumVo.setSmallNum(smallNum);
        resultNumVo.setPilotNum(pilotNum);
        resultNumVo.setSampleNum(sampleNum+batchNum);
        return resultNumVo;
    }

    @Override
    public ResultAppVo searchAppList(Long orgId, Long userId, String username, PatentResultParam patentResultParam) throws Exception {
        if(!("admin".equals(username))){
            patentResultParam.setEditorId(userId);
        }
        patentResultParam.setOrganizationId(orgId);
        // 查询专利成果列表
        PageInfo<PatentResult> patentResultPageInfo = this.selectMany(patentResultParam);
        // 查询专利成果统计数据
        ResultNumVo resultNumVo = this.searchResultNum(orgId,userId,username);

        ResultAppVo resultAppVo = new ResultAppVo();
        resultAppVo.setPatentResultList(patentResultPageInfo);
        resultAppVo.setSmallNum(resultNumVo.getSmallNum());
        resultAppVo.setPilotNum(resultNumVo.getPilotNum());
        resultAppVo.setSampleNum(resultNumVo.getSampleNum());
        return resultAppVo;
    }

    @Override
    public ResultWebNumVo searchStatistics(Long orgId, Long userId, String username) throws Exception{
        PatentResultParam patentResultParam = new PatentResultParam();
        patentResultParam.setPageNum(1);
        patentResultParam.setPageSize(1);
        patentResultParam.setOrganizationId(orgId);
        if(!("admin".equals(username))){
            patentResultParam.setEditorId(userId);
        }
        // 获取通过小试的专利成果数
        patentResultParam.setMaturity(PatentResultMaturityEnum.SMALL_TEST.name());
        Long smallNum = this.selectMany(patentResultParam).getTotal();
        // 获取通过中试的专利成果数
        patentResultParam.setMaturity(PatentResultMaturityEnum.PILOT_TEST.name());
        Long pilotNum = this.selectMany(patentResultParam).getTotal();
        // 获取可以量产的专利成果数
        patentResultParam.setMaturity(PatentResultMaturityEnum.BATCH_PRODUCTION.name());
        Long batchNum = this.selectMany(patentResultParam).getTotal();
        // 获取已有样品的专利成果数
        patentResultParam.setMaturity(PatentResultMaturityEnum.SAMPLE.name());
        Long sampleNum = this.selectMany(patentResultParam).getTotal();

        ResultWebNumVo resultNumVo = new ResultWebNumVo();
        resultNumVo.setSmallNum(smallNum);
        resultNumVo.setPilotNum(pilotNum);
        resultNumVo.setSampleNum(sampleNum);
        resultNumVo.setBatchNum(batchNum);
        resultNumVo.setTotalNum(smallNum+pilotNum+sampleNum+batchNum);
        return resultNumVo;
    }

    @Override
    public Map searchStatisticsByTeam(Long orgId, Long userId, String username) {
        PatentResultParam patentResultParam = new PatentResultParam();
        patentResultParam.setOrganizationId(orgId);
        if(!("admin".equals(username))){
            patentResultParam.setEditorId(userId);
        }
        List<Map<String, Object>> maps = patentResultMapper.searchStatisticsByTeam(patentResultParam);
        if(maps.size()>10){
            maps=maps.subList(0,10);
        }
        return builderList(maps);
    }

    @Override
    public Map<String, List<Object>> searchStatisticsByUser(Long orgId, Long userId, String username) {
        if(!("admin".equals(username))){
            return null;
        }
        List<Map<String, Object>> maps = patentResultMapper.searchStatisticsByUser(orgId);
        if(maps.size()>10){
            maps=maps.subList(0,10);
        }
        return builderList(maps);
    }

    @Override
    public String getResultNum() {
        return "CG"+ UUidUtils.getUUId(2);
    }


    private void addPatentResultVo(PatentResultVo patentResultVo) throws Exception {
        //添加专利成果附件
        List<PatentResultAttachment> patentResultAttachmentList = patentResultVo.getPatentResultAttachmentList();
        if(patentResultAttachmentList != null && patentResultAttachmentList.size() > 0){
            //删除原有的附件
            patentResultAttachmentService.deleteByField("patent_result_id",patentResultVo.getId(),null);
            for(PatentResultAttachment patentResultAttachment:patentResultAttachmentList){
                patentResultAttachment.setPatentResultId(patentResultVo.getId());
                patentResultAttachmentService.check(patentResultAttachment);
                patentResultAttachmentService.insert(patentResultAttachment);
            }
        }
        //添加专利成果图片
        List<PatentResultImage> patentResultImageList = patentResultVo.getPatentResultImageList();
        if(patentResultImageList != null && patentResultImageList.size() > 0){
            //删除原有的图片
            patentResultImageService.deleteByField("patent_result_id", patentResultVo.getId(), null);
            for(PatentResultImage patentResultImage:patentResultImageList){
                patentResultImage.setPatentResultId(patentResultVo.getId());
                patentResultImageService.check(patentResultImage);
                patentResultImageService.insert(patentResultImage);
            }
        }
        //添加专利成果标签值
        List<LabelValue> labelValueList = patentResultVo.getLabelValueList();
        if(labelValueList != null && labelValueList.size() > 0){
            //删除原有的标签值
            patentResultLabelService.deleteByField("patent_result_id", patentResultVo.getId(), null);
            for(LabelValue labelValue:labelValueList){
                PatentResultLabel patentResultLabel = new PatentResultLabel();
                patentResultLabel.setPatentResultId(patentResultVo.getId());
                patentResultLabel.setLabelValueId(labelValue.getId());
                patentResultLabel.setLabelId(labelValue.getLabelId());
                patentResultLabelService.check(patentResultLabel);
                patentResultLabelService.insert(patentResultLabel);
            }
        }
    }

    public static  Map<String, List<Object>> builderList(List<Map<String, Object>> sourceList) {
        if (sourceList.isEmpty())
            return new HashMap<>();
        return sourceList.get(0).keySet().stream().collect(Collectors.toMap(key -> key, key -> sourceList.stream().map(stringObjectMap -> stringObjectMap.get(key)).collect(Collectors.toList())));
    }

}

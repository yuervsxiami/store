package com.cnuip.colligate.service;

import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.vo.PatentInfo;
import com.cnuip.colligate.vo.PatentNumVo;
import com.cnuip.colligate.vo.PatentValueVo;
import com.cnuip.colligate.vo.PatentVo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xjt on 2018/10/9.
 */
public interface PatentService {

    /**
     * 获取专利列表
     * @param userId
     * @param username
     * @param patentVo
     * @return
     */
    LinkedHashMap selectPatentList(Long userId, String username, Long orgId, PatentVo patentVo) throws Exception;

    /**
     * 获取专利价值列表
     * @param userId
     * @param username
     * @param patentVo
     * @return
     */
    Object selectPatentValueList(Long userId, String username, Long orgId, PatentVo patentVo) throws Exception;

    /**
     * 获取专利详情
     * @param an
     * @return
     */
    Object selectPatentDetail(String an) throws Exception;

    /**
     * 获取专利报告
     * @param an
     * @return
     */
    Object selectPatentReport(String an) throws Exception;

    ArrayList selectSelfPatent(Long userId, String anOrTi) throws Exception;

    Object selectStatistics(Long userId, Long orgId) throws Exception;

    PatentNumVo selectUseStatistics(Long userId) throws Exception;

    Object selectQuoteList(String an) throws Exception;

    Object selectSimilarityInfo(String an, Long pageNum, Long pageSize) throws Exception;

    PatentInfo selectKnowledgeReport(Long userId, String username, Long orgId) throws Exception;

    List<PatentValueVo> priceReport(Long userId, String username, Long orgId) throws  Exception;

    Map selectwebStatistics(Long userId, Long orgId) throws  Exception;

    Map<String, Object> collegeContributeRank(Long userId, String username, Long orgId) throws ClientException;

    List<Map<String, Object>> collegeTransCount(Long userId, String username, Long orgId) throws ClientException;

    List<Map<String, Object>> getCollegeApplyYear(Long userId, String username, Long orgId) throws ClientException;

    Map<String, Object> getCollegePaCount(Long userId, String username, Long orgId) throws ClientException;

    List<Map<String, Object>> professorApplyYear(Long userId, String username, Long orgId) throws ClientException;

    Map<String, Object> professorValueRank(Long userId, String username, Long orgId) throws ClientException;

    List<Map<String, Object>> professorTransCount(Long userId, String username, Long orgId) throws ClientException;
}

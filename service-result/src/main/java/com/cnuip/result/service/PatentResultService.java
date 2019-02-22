package com.cnuip.result.service;

import com.cnuip.base.domain.params.PatentResultParam;
import com.cnuip.base.domain.result.PatentResult;
import com.cnuip.base.service.AbstractService;
import com.cnuip.result.vo.PatentResultVo;
import com.cnuip.result.vo.ResultAppVo;
import com.cnuip.result.vo.ResultNumVo;
import com.cnuip.result.vo.ResultWebNumVo;

import java.util.List;
import java.util.Map;

/**
 * Created by xjt on 2018/8/30.
 */
public interface PatentResultService extends AbstractService<PatentResult, PatentResultParam> {

    /**
     * 新增专利成果
     * @param patentResultVo
     * @return
     */
    PatentResultVo addPatentResult(PatentResultVo patentResultVo) throws Exception;

    /**
     * 编辑专利成果
     * @param patentResultVo
     * @return
     */
    PatentResultVo updatePatentResult(PatentResultVo patentResultVo) throws Exception;

    /**
     * 查询专利成果详情
     * @param patentResultId
     * @return
     */
    PatentResultVo selectPatentResultDetail(Long patentResultId) throws Exception;

    /**
     * 删除专利成果
     * @param patentResultId
     * @return
     */
    PatentResultVo deletePatentResult(long patentResultId) throws Exception;

    ResultNumVo searchResultNum(Long orgId, Long userId, String username) throws Exception;

    ResultAppVo searchAppList(Long orgId, Long userId, String username, PatentResultParam patentResultParam) throws Exception;

    ResultWebNumVo searchStatistics(Long orgId, Long userId, String username) throws Exception;

    Map<String, List<Object>> searchStatisticsByTeam(Long orgId, Long userId, String username);

    Map<String, List<Object>> searchStatisticsByUser(Long orgId, Long userId, String username);

    String getResultNum();
}

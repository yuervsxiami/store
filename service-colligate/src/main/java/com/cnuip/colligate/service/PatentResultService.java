package com.cnuip.colligate.service;

import com.cnuip.colligate.vo.PatentResultVo;

/**
 * Created by xjt on 2018/8/30.
 */
public interface PatentResultService {

    /**
     * 新增专利成果
     *
     * @param patentResultVo
     * @return
     */
    PatentResultVo addPatentResult(PatentResultVo patentResultVo) throws Exception;
}

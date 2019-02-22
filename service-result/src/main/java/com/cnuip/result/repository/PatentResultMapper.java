package com.cnuip.result.repository;

import com.cnuip.base.domain.params.PatentResultParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xjt on 2018/8/30.
 */
@Repository
public interface PatentResultMapper {

    List<Map<String,Object>> searchStatisticsByTeam(@Param("params") PatentResultParam patentResultParam);

    List<Map<String,Object>> searchStatisticsByUser(@Param("orgId") Long orgId);
}

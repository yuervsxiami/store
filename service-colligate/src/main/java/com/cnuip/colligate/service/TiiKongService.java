package com.cnuip.colligate.service;

import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.vo.PatentInfo;

import java.util.List;
import java.util.Map;

public interface TiiKongService {

    Object invoke(Map<String, Object> params) throws Exception;

    String getUserToken(Long userId) throws Exception;

    List getCompanyRecommendData(Long userId, String username) throws ClientException;

    PatentInfo getScientificInfo(Long userId, String username) throws Exception;

    PatentInfo getSimiliarExperts(Long userId, String username) throws Exception;

    List companyDevelopDirection(Long userId, String username) throws Exception;
}

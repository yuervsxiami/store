package com.cnuip.colligate.service;

import com.cnuip.colligate.vo.domain.ConvType;
import com.cnuip.colligate.vo.domain.DeleteClass;
import com.cnuip.colligate.vo.domain.PushModel;

import java.util.List;

/**
 * Created by xjt on 2018/10/9.
 */
public interface PushService {

    Object getRecords(String clientId, ConvType convType) throws Exception ;

    /**
     * @param clientId
     * @param convType
     * @param deleteClazz
     * @return
     * @throws Exception
     */
    Object deleteMessages(String clientId, ConvType convType, List<DeleteClass> deleteClazz) throws Exception;

    Object subscriber(String clientId, ConvType convType) throws Exception ;

    Object push(PushModel pushBody) throws Exception ;

    Object pushRecord(String objectId) throws Exception ;
}

package com.cnuip.process.service;


import com.cnuip.base.domain.params.ProcessRequisitionParam;
import com.cnuip.base.domain.process.ProcessRequisition;
import com.cnuip.base.service.AbstractService;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface ProcessRequisitionService extends AbstractService<ProcessRequisition, ProcessRequisitionParam> {

    List<ProcessRequisition> addProcessRequisition(List<ProcessRequisition> processRequisitionList) throws Exception ;
}

package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnLogisticsOrderProduct;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.repository.TnLogisticsOrderProductRepository;
import com.ihomefnt.aladdin.dms.domain.DeliverProductStatus;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.repository.DeliverProductStatusRepository;
import com.ihomefnt.aladdin.psi.controller.cargolabel.constant.LabelSourceConstant;
import com.ihomefnt.aladdin.psi.controller.cargolabel.dao.CargoLabelDao;
import com.ihomefnt.aladdin.psi.controller.cargolabel.dto.CargoLabel;
import com.ihomefnt.aladdin.psi.dao.LogisticsOrderDao;
import com.ihomefnt.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 物流处理
 */

@Slf4j
@Service
public class LogisticsProcessor implements Processor {

    @Autowired
    private TnLogisticsOrderProductRepository logisticsOrderProductRepository;

    @Autowired
    private CargoLabelDao cargoLabelDao;

    @Autowired
    private DeliverProductStatusRepository deliverProductStatusRepository;

    @Autowired
    private LogisticsOrderDao logisticsOrderDao;

    @Override
    public void process(ProductChangeContext context) {

        if (CollectionUtils.isEmpty(context.getChangeProductVos())) {
            return;
        }

        List<String> deliverKeys = new ArrayList<>();
        context.getChangeProductVos().forEach(item -> {
            if (!CollectionUtils.isEmpty(item.getDeliverKeys())) {
                deliverKeys.addAll(item.getDeliverKeys());
            }
        });

        newDelLogisticsOrderCargoDeal(deliverKeys);
    }

    /**
     * 删除物流订单货物
     */
    public void newDelLogisticsOrderCargoDeal(List<String> deliverKeys) {

        if (CollectionUtils.isEmpty(deliverKeys)) {
            return;
        }

        // 物流依据dk，数量1可直接操作删除
        List<TnLogisticsOrderProduct> logisticsOrderProducts = logisticsOrderProductRepository.queryLogisticsProductByDks(deliverKeys);
        if (CollectionUtils.isEmpty(logisticsOrderProducts)) {
            return;
        }

        logisticsOrderProductRepository.delLogisticsProductByDeliverKeys(deliverKeys);

        // 重新计算物流订单信息
        newReClacLogisticOrder(logisticsOrderProducts);

        // 操作货物标签信息
        newDelCargoLabelDb(logisticsOrderProducts, deliverKeys);

        log.info("newDelLogisticsOrderCargoDeal finish detail  {},",JsonUtil.toString(logisticsOrderProducts));
    }

    private void newDelCargoLabelDb(List<TnLogisticsOrderProduct> logisticsOrderProducts, List<String> deliverKeys) {

        if (CollectionUtils.isEmpty(logisticsOrderProducts)) {
            return;
        }

        Map<String, Integer> labelChangeMap = new HashMap<>();

        List<CargoLabel> labelList = new ArrayList<>();
        logisticsOrderProducts.forEach(v -> {

            Integer count = labelChangeMap.get(v.lableKey());
            if (null == count) {
                count = 0;

                // 查询标签信息
                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("superKey", v.getSuperKey());
                paramsMap.put("cargoId", v.getSkuId());
                paramsMap.put("sourceId", v.getWlOrderId());
                paramsMap.put("source", LabelSourceConstant.SYS_GENERATE.getStatus());
                CargoLabel cargoLabel = cargoLabelDao.queryCargoLabelByParam(paramsMap);
                if (null != cargoLabel) {
                    labelList.add(cargoLabel);
                }
            }

            // 货物变更数量
            count++;
            labelChangeMap.put(v.lableKey(), count);

        });

        // 查询货物单件包裹数
        Map<String, BigDecimal> productStatusMap = new HashMap<>();
        List<DeliverProductStatus> productStatusList = deliverProductStatusRepository.batchEffectByDeliverKeys(deliverKeys);
        if (!CollectionUtils.isEmpty(productStatusList)) {
            productStatusMap = productStatusList.stream().collect(Collectors.toMap(DeliverProductStatus::lableKey, DeliverProductStatus::getPackageCount));
        }

        if (!CollectionUtils.isEmpty(labelList)) {

            List<Long> cargoLabelIds = new ArrayList<>();
            List<CargoLabel> updateList = new ArrayList<>();
            for (CargoLabel v : labelList) {
                Integer count = labelChangeMap.get(v.lableKey());
                BigDecimal packCount = productStatusMap.get(v.lableKey());
                if (null == count || null == packCount) {
                    continue;
                }

                // 变更数量与标签内数量比较
                Integer newCount = v.getCargoCount() - count;
                if (newCount > 0) {
                    CargoLabel updateObj = new CargoLabel();
                    updateObj.setId(v.getId());
                    updateObj.setCargoCount(newCount);
                    updateObj.setPackageSum(Double.valueOf(newCount * packCount.intValue()));
                    updateList.add(updateObj);
                } else {
                    cargoLabelIds.add(v.getId());
                }
            }

            if (CollectionUtils.isEmpty(updateList)) {
                cargoLabelDao.updateCargoCountBatchByIds(updateList);
            }

            if (!CollectionUtils.isEmpty(cargoLabelIds)) {
                Map<String, Object> map = new HashMap<>();
                map.put("cargoLabelIds", cargoLabelIds);
                cargoLabelDao.deleteCargoLabelByParam(map);
            }
        }

    }

    /**
     * 重新计算物流订单信息
     *
     * @param logisticsOrderProducts
     */
    private void newReClacLogisticOrder(List<TnLogisticsOrderProduct> logisticsOrderProducts) {

        if (CollectionUtils.isEmpty(logisticsOrderProducts)) {
            return;
        }

        Set<Integer> wlOrderIds = logisticsOrderProducts.stream().map(v -> v.getWlOrderId().intValue()).collect(Collectors.toSet());
        List<TnLogisticsOrderProduct> productList = logisticsOrderProductRepository.queryProductsByLogisticsIds(wlOrderIds);
        if (CollectionUtils.isEmpty(productList)) {
            // 物流订单下无商品删除物流订单
            logisticsOrderDao.delLogisticsOrderByParam(wlOrderIds);
            return;
        }

        Map<Long, List<TnLogisticsOrderProduct>> productMap = productList.stream().collect(Collectors.groupingBy(TnLogisticsOrderProduct::getWlOrderId));

        // 物流订单下无商品删除物流订单
        Set<Integer> delLogisticsIds = new HashSet<>();
        wlOrderIds.forEach(item -> {
            if (!productMap.containsKey(item.longValue())) {
                delLogisticsIds.add(item);
            }
        });

        if (!CollectionUtils.isEmpty(delLogisticsIds)) {
            logisticsOrderDao.delLogisticsOrderByParam(delLogisticsIds);
        }

        List<String> deliverKeys = productList.stream().map(TnLogisticsOrderProduct::getDeliverKey).collect(Collectors.toList());
        List<DeliverProductStatus> productStatusList = deliverProductStatusRepository.batchEffectByDeliverKeys(deliverKeys);

        if (!CollectionUtils.isEmpty(productStatusList)) {
            Map<String, DeliverProductStatus> productStatusMap = productStatusList.stream().collect(Collectors.toMap(DeliverProductStatus::getDeliverKey, v -> v));
            for (Integer wlOrderId : wlOrderIds) {

                List<TnLogisticsOrderProduct> list = productMap.get(wlOrderId.longValue());
                if (CollectionUtils.isEmpty(list)) {
                    continue;
                }

                int cargoNumber = 0;
                int pacNumber = 0;
                BigDecimal volums = BigDecimal.ZERO;
                BigDecimal weight = BigDecimal.ZERO;
                for (TnLogisticsOrderProduct resultDto : list) {
                    DeliverProductStatus productStatus = productStatusMap.get(resultDto.getDeliverKey());
                    if (null == productStatus) {
                        continue;
                    }
                    cargoNumber++;
                    pacNumber += null == productStatus.getPackageCount() ? 0 : productStatus.getPackageCount().intValue();
                    volums = volums.add(productStatus.getVolume());
                    weight = weight.add(productStatus.getWeight());
                }

                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("id", wlOrderId);
                paramMap.put("cargoCount", cargoNumber);
                paramMap.put("pacCount", pacNumber);
                paramMap.put("orderCargoVolumns", volums);
                paramMap.put("orderCargoWeight", weight);
                if (cargoNumber == 0) {
                    paramMap.put("orderStatus", 1);
                }
                logisticsOrderDao.updateLogisticsOrderSumInfoByParam(paramMap);
            }
        }

        log.info("newReClacLogisticOrder finish detail  {},", JsonUtil.toString(logisticsOrderProducts));
    }
}

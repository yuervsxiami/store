package com.cnuip.colligate.client;

import com.cnuip.colligate.vo.PatentResultVo;
import com.cnuip.gaea.service.web.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "store2-service-result")
public interface PatentResultClient {

    @PostMapping("/v1/patentresult/base")
    ApiResponse<PatentResultVo> addPatentResult(@RequestBody PatentResultVo patentResultVo) throws Exception ;
}

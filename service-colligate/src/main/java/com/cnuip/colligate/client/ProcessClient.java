package com.cnuip.colligate.client;

import com.cnuip.base.domain.process.ProcessTask;
import com.cnuip.base.domain.process.ProcessTaskUser;
import com.cnuip.colligate.vo.ProcessVo;
import com.cnuip.gaea.service.web.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "store2-service-process")
public interface ProcessClient {
    @PostMapping("/v1/process/base")
    ApiResponse<ProcessVo> addProcess(@RequestBody ProcessVo processVo) throws Exception ;

    @PostMapping("/v1/process/task/user/")
    ApiResponse<ProcessTaskUser> addProcessTaskUser(@RequestBody ProcessTaskUser processTaskUser) throws Exception ;

    @GetMapping("/v1/process/task/")
    ApiResponse<ProcessTask> queryProcessTask(@RequestParam(value = "processId") Long processId, @RequestParam(value = "tmplProcessTaskId") Long tmplProcessTaskId) throws Exception ;

}

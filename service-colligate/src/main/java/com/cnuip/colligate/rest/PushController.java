package com.cnuip.colligate.rest;

import com.cnuip.colligate.service.PushService;
import com.cnuip.colligate.vo.domain.ConvType;
import com.cnuip.colligate.vo.domain.DeleteClass;
import com.cnuip.colligate.vo.domain.PushModel;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/push")
@Api(value = "PushController", description = "消息推送接口")
public class PushController extends ControllerResponse {

    @Autowired
    private PushService pushService;

    /**
     * 订阅服务号
     *
     * @param clientId
     * @param convType
     * @return
     */
    @GetMapping("/messages")
    public ApiResponse<Object> messages(@RequestParam String clientId, @RequestParam ConvType convType) throws Exception {
        return ok(pushService.getRecords(clientId, convType));
    }

    /**
     * 订阅服务号
     *
     * @param clientId
     * @param convType
     * @return
     */
    @PostMapping("/subscriber")
    public ApiResponse<Object> subscriber(@RequestParam String clientId, @RequestParam ConvType convType) throws Exception {
        return ok(pushService.subscriber(clientId, convType));
    }

    /**
     * 推送所有设备
     *
     * @return
     */
    @PostMapping("/")
    public ApiResponse<Object> push(@RequestBody PushModel pushBody) throws Exception {
        return ok(pushService.push(pushBody));
    }

    /**
     * 查询推送记录
     *
     * @return
     */
    @GetMapping("/record")
    public ApiResponse<Object> pushRecord(String objectId) throws Exception {
        return ok(pushService.pushRecord(objectId));
    }

    /**
     * 删除给用户单独发送的消息
     * @return
     */
    @DeleteMapping("/")
    @ApiOperation(notes = "删除推送消息", value = "删除推送消息", produces = "application/json")
    public ApiResponse<Object> delete(@RequestParam String clientId, @RequestParam ConvType convType, @RequestBody List<DeleteClass> deleteClazz) throws Exception{
        return ok(pushService.deleteMessages(clientId, convType, deleteClazz));
    }
}

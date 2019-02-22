package com.cnuip.process.rest;

import com.cnuip.base.domain.params.ProcessParam;
import com.cnuip.base.domain.process.Process;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.UrlUtils;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.process.service.ProcessService;
import com.cnuip.process.vo.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/process")
@Api(value = "ProcessController", description = "提案接口")
public class ProcessController extends ControllerResponse {

    @Autowired
    private ProcessService processService;

    @ApiIgnore
    @PostMapping("/base")
    @ApiOperation(notes = "新增提案（该接口仅内部使用，外部调用接口在colligate中）", value = "新增提案", produces = "application/json")
    public ApiResponse<ProcessVo> add(@RequestBody ProcessVo processVo) throws Exception {
        return ok(processService.addProcess(processVo));
    }

    @PutMapping("/")
    @ApiOperation(notes = "修改提案状态", value = "修改提案状态", produces = "application/json")
    public ApiResponse<Process> update(@RequestBody Process process) throws Exception {
        return ok(processService.updateByKey(process.getId(), process));
    }

    @GetMapping("/")
    @ApiOperation(notes = "提案列表(审核列表)", value = "提案列表(审核列表)", produces = "application/json")
    public ApiResponse<PageInfo<ProcessListVo>> search(@RequestHeader("X-Request-UserId") Long userId,
                                                       @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                       ProcessParam processParam) throws Exception {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = UrlUtils.decode(servletRequest.getHeader("X-Request-UserName"));
        processParam.setOrganizationId(orgId);
        return ok(processService.selectProcess(userId,username,processParam));
    }

    @GetMapping("/editor")
    @ApiOperation(notes = "提案列表(操作列表)", value = "提案列表(操作列表)", produces = "application/json")
    public ApiResponse<PageInfo<Process>> searchList(@RequestHeader("X-Request-UserId") Long userId,
                                                     @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                     ProcessParam processParam) throws Exception {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = UrlUtils.decode(servletRequest.getHeader("X-Request-UserName"));
        processParam.setOrganizationId(orgId);
        return ok(processService.selectEditorProcess(userId,username,processParam));
    }

    @GetMapping("/copyme")
    @ApiOperation(notes = "提案列表(抄送列表)", value = "提案列表(抄送列表)", produces = "application/json")
    public ApiResponse<PageInfo<Process>> searchCopyList(@RequestHeader("X-Request-UserId") Long userId,
                                                         @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                         ProcessParam processParam) throws Exception{
        processParam.setOrganizationId(orgId);
        return ok(processService.selectCopyProcess(userId,processParam));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除提案", value = "删除提案", produces = "application/json")
    public ApiResponse<ProcessVo> delete(@RequestParam("processId") Long processId) throws Exception{
        return ok(processService.deleteProcess(processId));
    }

    @GetMapping("/detail")
    @ApiOperation(notes = "查看提案详情", value = "查看提案详情", produces = "application/json")
    public ApiResponse<ProcessVo> selectProcessDetail(@RequestParam("processId") Long processId) throws Exception{
        return ok(processService.selectProcessDetail(processId));
    }

    @GetMapping("/user")
    @ApiOperation(notes = "提案人列表", value = "提案人列表", produces = "application/json")
    public ApiResponse<List<User>> queryProcessUser(@RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception{
        return ok(processService.selectProcessUser(orgId));
    }

    @GetMapping("/statistics")
    @ApiOperation(notes = "提案列表统计(app调用)", value = "提案列表统计(app调用)", produces = "application/json")
    public ApiResponse<ProcessNumVo> searchProcessNum(@RequestHeader("X-Request-UserId") Long userId, @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception{
        return ok(processService.searchProcessNum(userId,orgId));
    }

    @GetMapping("/app/editor")
    @ApiOperation(notes = "提案列表(操作列表,app调用)", value = "提案列表(操作列表,app调用)", produces = "application/json")
    public ApiResponse<ProcessAppVo> searchAppList(@RequestHeader("X-Request-UserId") Long userId,
                                                   @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                   ProcessParam processParam) throws Exception {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = UrlUtils.decode(servletRequest.getHeader("X-Request-UserName"));
        processParam.setOrganizationId(orgId);
        return ok(processService.selectAppProcess(userId,username,processParam));
    }

    @GetMapping("/app/audit")
    @ApiOperation(notes = "提案列表(审核列表,app调用)", value = "提案列表(审核列表,app调用)", produces = "application/json")
    public ApiResponse<ProcessAppAuditVo> searchAppAudit(@RequestHeader("X-Request-UserId") Long userId,
                                                         @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                         ProcessParam processParam) throws Exception {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = UrlUtils.decode(servletRequest.getHeader("X-Request-UserName"));
        processParam.setOrganizationId(orgId);
        return ok(processService.searchAppAudit(userId,username,processParam));
    }

    @GetMapping("/app/copyme")
    @ApiOperation(notes = "提案列表(抄送列表,app调用)", value = "提案列表(抄送列表,app调用)", produces = "application/json")
    public ApiResponse<ProcessAppVo> searchAppCopy(@RequestHeader("X-Request-UserId") Long userId,
                                                   @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                   ProcessParam processParam) throws Exception{
        processParam.setOrganizationId(orgId);
        return ok(processService.searchAppCopy(userId,processParam));
    }

    @GetMapping("/processNum")
    @ApiOperation(notes = "获取提案唯一默认编号", value = "获取提案唯一默认编号", produces = "application/json")
    public ApiResponse<String> getProcessNum() throws Exception {
        return ok(processService.getProcessNum());
    }
}

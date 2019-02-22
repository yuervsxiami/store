package com.cnuip.result.rest;


import com.cnuip.base.domain.params.PatentResultParam;
import com.cnuip.base.domain.result.PatentResult;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.cnuip.result.service.PatentResultService;
import com.cnuip.result.vo.PatentResultVo;
import com.cnuip.result.vo.ResultAppVo;
import com.cnuip.result.vo.ResultNumVo;
import com.cnuip.result.vo.ResultWebNumVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1/patentresult")
@Api(value = "PatentResultController", description = "专利成果接口")
public class PatentResultController extends ControllerResponse {

    @Autowired
    private PatentResultService patentResultService;

    @ApiIgnore
    @PostMapping("/base")
    @ApiOperation(notes = "新增专利成果", value = "新增专利成果", produces = "application/json")
    public ApiResponse<PatentResultVo> add(@RequestBody PatentResultVo patentResultVo) throws Exception {
        return ok(patentResultService.addPatentResult(patentResultVo));
    }

    @PutMapping("/")
    @ApiOperation(notes = "编辑专利成果", value = "编辑专利成果", produces = "application/json")
    public ApiResponse<PatentResultVo> update(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                              @RequestBody PatentResultVo patentResultVo) throws Exception {
        patentResultVo.setOrganizationId(orgId);
        return ok(patentResultService.updatePatentResult(patentResultVo));
    }

    @GetMapping("/")
    @ApiOperation(notes = "专利成果列表", value = "专利成果列表", produces = "application/json")
    public ApiResponse<PageInfo<PatentResult>> search(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                      @RequestHeader("X-Request-UserId") Long userId,
                                                      @RequestHeader("X-Request-UserName") String username,
                                                      PatentResultParam patentResultParam) throws Exception {
        if(!("admin".equals(username))){
            patentResultParam.setEditorId(userId);
        }
        patentResultParam.setOrganizationId(orgId);
        return ok(patentResultService.selectMany(patentResultParam));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除专利成果", value = "删除专利成果", produces = "application/json")
    public ApiResponse<PatentResultVo> delete(@RequestParam("patentResultId") long patentResultId) throws Exception {
        return ok(patentResultService.deletePatentResult(patentResultId));
    }

    @GetMapping("/detail")
    @ApiOperation(notes = "查看专利成果详情", value = "查看专利成果详情", produces = "application/json")
    public ApiResponse<PatentResultVo> selectPatentResultDetail(@RequestParam("patentResultId") Long patentResultId) throws Exception {
        return ok(patentResultService.selectPatentResultDetail(patentResultId));
    }

    @GetMapping("/statistics")
    @ApiOperation(notes = "专利成果列表统计(app调用)", value = "专利成果列表统计(app调用)", produces = "application/json")
    public ApiResponse<ResultNumVo> searchResultNum(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                    @RequestHeader("X-Request-UserId") Long userId,
                                                    @RequestHeader("X-Request-UserName") String username) throws Exception{
        return ok(patentResultService.searchResultNum(orgId,userId,username));
    }

    @GetMapping("/app")
    @ApiOperation(notes = "专利成果列表(app调用)", value = "专利成果列表(app调用)", produces = "application/json")
    public ApiResponse<ResultAppVo> searchAppList(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                  @RequestHeader("X-Request-UserId") Long userId,
                                                  @RequestHeader("X-Request-UserName") String username,
                                                  PatentResultParam patentResultParam) throws Exception {
        return ok(patentResultService.searchAppList(orgId,userId,username,patentResultParam));
    }

    @GetMapping("/web/statistics")
    @ApiOperation(notes = "专利成果统计(web调用)", value = "专利成果统计(web调用)", produces = "application/json")
    public ApiResponse<ResultWebNumVo> searchStatistics(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                        @RequestHeader("X-Request-UserId") Long userId,
                                                        @RequestHeader("X-Request-UserName") String username) throws Exception{
        return ok(patentResultService.searchStatistics(orgId,userId,username));
    }

    @GetMapping("/web/user")
    @ApiOperation(notes = "成果研究数量排名(web调用)", value = "成果研究数量排名(web调用)", produces = "application/json")
    public ApiResponse<Map> searchStatisticsByUser(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                   @RequestHeader("X-Request-UserId") Long userId,
                                                   @RequestHeader("X-Request-UserName") String username) throws Exception{
        return ok(patentResultService.searchStatisticsByUser(orgId,userId,username));
    }

    @GetMapping("/web/team")
    @ApiOperation(notes = "项目组成果数量排名(web调用)", value = "项目组成果数量排名(web调用)", produces = "application/json")
    public ApiResponse<Map> searchStatisticsByTeam(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                                    @RequestHeader("X-Request-UserId") Long userId,
                                                    @RequestHeader("X-Request-UserName") String username) throws Exception{
        return ok(patentResultService.searchStatisticsByTeam(orgId,userId,username));
    }


    @GetMapping("/resultNum")
    @ApiOperation(notes = "获取成果唯一默认编号", value = "获取成果唯一默认编号", produces = "application/json")
    public ApiResponse<String> getResultNum() throws Exception {
        return ok(patentResultService.getResultNum());
    }
}

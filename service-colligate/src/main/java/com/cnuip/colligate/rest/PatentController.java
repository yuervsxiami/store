package com.cnuip.colligate.rest;


import com.cnuip.colligate.service.PatentService;
import com.cnuip.colligate.vo.PatentInfo;
import com.cnuip.colligate.vo.PatentNumVo;
import com.cnuip.colligate.vo.PatentValueVo;
import com.cnuip.colligate.vo.PatentVo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/patent")
@Api(value = "PatentController", description = "专利接口")
public class PatentController extends ControllerResponse {

    @Autowired
    private PatentService patentService;

    @GetMapping("/")
    @ApiOperation(notes = "获取专利列表", value = "获取专利列表", produces = "application/json")
    public ApiResponse<LinkedHashMap> selectPatentList(@RequestHeader("X-Request-UserId") Long userId,
                                                       @RequestHeader("X-Request-UserName") String username,
                                                       @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                       PatentVo patentVo) throws Exception {
        return ok(patentService.selectPatentList(userId, username, orgId, patentVo));
    }

    @GetMapping("/value")
    @ApiOperation(notes = "获取专利价值列表", value = "获取专利价值列表", produces = "application/json")
    public ApiResponse<Object> selectPatentValueList(@RequestHeader("X-Request-UserId") Long userId,
                                                     @RequestHeader("X-Request-UserName") String username,
                                                     @RequestHeader("X-Request-OrganizationId") Long orgId,
                                                     PatentVo patentVo) throws Exception {
        return ok(patentService.selectPatentValueList(userId, username, orgId, patentVo));
    }

    @GetMapping("/detail")
    @ApiOperation(notes = "获取专利详情", value = "获取专利详情", produces = "application/json")
    public ApiResponse<Object> selectPatentDetail(@RequestParam("an") String an) throws Exception {
        return ok(patentService.selectPatentDetail(an));
    }

    @GetMapping("/report")
    @ApiOperation(notes = "获取专利报告", value = "获取专利报告", produces = "application/json")
    public ApiResponse<Object> selectPatentReport(@RequestParam("an") String an) throws Exception {
        return ok(patentService.selectPatentReport(an));
    }

    @GetMapping("/patentQuoteList")
    @ApiOperation(notes = "获取专利引证", value = "获取专利引证", produces = "application/json")
    public ApiResponse<Object> selectQuoteList(@RequestParam("an") String an) throws Exception {
        return ok(patentService.selectQuoteList(an));
    }

    @GetMapping("/patentSimilarityInfo")
    @ApiOperation(notes = "获取专利相似度", value = "获取专利相似度", produces = "application/json")
    public ApiResponse<Object> selectSimilarityInfo(@RequestParam("an") String an,
                                                    Long pageNum, Long pageSize) throws Exception {
        return ok(patentService.selectSimilarityInfo(an, pageNum, pageSize));
    }

    @GetMapping("/useful")
    @ApiOperation(notes = "获取专利列表(当前用户可委托的专利列表)", value = "获取专利列表(当前用户可委托的专利列表)", produces = "application/json")
    public ApiResponse<ArrayList> selectSelfPatent(@RequestHeader("X-Request-UserId") Long userId,
                                                   String anOrTi) throws Exception {
        return ok(patentService.selectSelfPatent(userId, anOrTi));
    }

    @GetMapping("/statistics")
    @ApiOperation(notes = "获取专利分类统计信息(app调用)", value = "获取专利分类统计信息(app调用)", produces = "application/json")
    public ApiResponse<Object> selectStatistics(@RequestHeader("X-Request-UserId") Long userId,
                                                @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.selectStatistics(userId, orgId));
    }

    @GetMapping("/webStatistics")
    @ApiOperation(notes = "获取专利分类统计信息(web调用)", value = "获取专利分类统计信息(web调用)", produces = "application/json")
    public ApiResponse<Map> webStatistics(@RequestHeader("X-Request-UserId") Long userId,
                                           @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.selectwebStatistics(userId, orgId));
    }


    @GetMapping("/use/statistics")
    @ApiOperation(notes = "获取专利使用统计信息(app调用)", value = "获取专利使用统计信息(app调用)", produces = "application/json")
    public ApiResponse<PatentNumVo> selectUseStatistics(@RequestHeader("X-Request-UserId") Long userId) throws Exception {
        return ok(patentService.selectUseStatistics(userId));
    }

    @GetMapping("/use/report")
    @ApiOperation(notes = "获取知产报告", value = "获取知产报告", produces = "application/json")
    public ApiResponse<PatentInfo> selectKnowledgeReport(@RequestHeader("X-Request-UserId") Long userId,
                                                         @RequestHeader("X-Request-UserName") String username,
                                                         @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.selectKnowledgeReport(userId, username, orgId));
    }

    @GetMapping("/use/priceReport")
    @ApiOperation(notes = "获取专利价格报告", value = "获取专利价格报告", produces = "application/json")
    public ApiResponse<List<PatentValueVo>> priceReport(@RequestHeader("X-Request-UserId") Long userId,
                                                        @RequestHeader("X-Request-UserName") String username,
                                                        @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.priceReport(userId, username, orgId));
    }

    @GetMapping("/use/collegeContributeRank")
    @ApiOperation(notes = "获取高校贡献前十", value = "获取高校贡献前十", produces = "application/json")
    public ApiResponse<Map<String, Object>> collegeContributeRank(@RequestHeader("X-Request-UserId") Long userId,
                                                                  @RequestHeader("X-Request-UserName") String username,
                                                                  @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.collegeContributeRank(userId, username, orgId));
    }

    @GetMapping("/use/collegeTransCount")
    @ApiOperation(notes = "获取高校历年交易数", value = "获取高校历年交易数", produces = "application/json")
    public ApiResponse<List<Map<String, Object>>> collegeTransCount(@RequestHeader("X-Request-UserId") Long userId,
                                                                    @RequestHeader("X-Request-UserName") String username,
                                                                    @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.collegeTransCount(userId, username, orgId));
    }

    @GetMapping("/use/collegeApplyYear")
    @ApiOperation(notes = "获取高校申请年份", value = "获取高校申请年份", produces = "application/json")
    public ApiResponse<List<Map<String, Object>>> collegeApplyYear(@RequestHeader("X-Request-UserId") Long userId,
                                                                    @RequestHeader("X-Request-UserName") String username,
                                                                    @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.getCollegeApplyYear(userId, username, orgId));
    }

    @GetMapping("/use/getCollegePaCount")
    @ApiOperation(notes = "获取申请人维度下高校专利统计", value = "获取申请人维度下高校专利统计", produces = "application/json")
    public ApiResponse<Map<String, Object>> getCollegePaCount(@RequestHeader("X-Request-UserId") Long userId,
                                                                   @RequestHeader("X-Request-UserName") String username,
                                                                   @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.getCollegePaCount(userId, username, orgId));
    }

    @GetMapping("/use/professorApplyYear")
    @ApiOperation(notes = "获取专家申请年份排名", value = "获取专家申请年份排名", produces = "application/json")
    public ApiResponse<List<Map<String, Object>>> professorApplyYear(@RequestHeader("X-Request-UserId") Long userId,
                                                              @RequestHeader("X-Request-UserName") String username,
                                                              @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.professorApplyYear(userId, username, orgId));
    }

    @GetMapping("/use/professorValueRank")
    @ApiOperation(notes = "获取专家专利价值排名", value = "获取专家专利价值排名", produces = "application/json")
    public ApiResponse<Map<String, Object>> professorValueRank(@RequestHeader("X-Request-UserId") Long userId,
                                                                  @RequestHeader("X-Request-UserName") String username,
                                                                  @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.professorValueRank(userId, username, orgId));
    }

    @GetMapping("/use/professorTransCount")
    @ApiOperation(notes = "获取专家历年交易数", value = "获取专家历年交易数", produces = "application/json")
    public ApiResponse<List<Map<String, Object>>> professorTransCount(@RequestHeader("X-Request-UserId") Long userId,
                                                                     @RequestHeader("X-Request-UserName") String username,
                                                                     @RequestHeader("X-Request-OrganizationId") Long orgId) throws Exception {
        return ok(patentService.professorTransCount(userId, username, orgId));
    }

}

package com.cnuip.colligate.rest;

import com.cnuip.colligate.service.TiiKongService;
import com.cnuip.colligate.vo.PatentInfo;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "TiiKongController", description = "天弓接口")
public class TiiKongController extends ControllerResponse {
    private TiiKongService tiiKongService;

    public TiiKongController(TiiKongService tiiKongService) {
        this.tiiKongService = tiiKongService;
    }

    @GetMapping("/tiikong")
    @ApiOperation(notes = "1.相关词检索接口\n" +
            "name:getCorrelationKeyWordSearch\n" +
            "keywords:一个或多个词语(词1|词2|词3)\n" +
            "topn:查询多少笔数据\n" +
            "\n" +
            "2.内容关键词接口\n" +
            "name:getContentKeyWordSearch\n" +
            "text:一段文本\n" +
            "topn:查询多少笔数据\n" +
            "pos:默认n|vn|ns|vn(v:动词,n:名词,ns:名词短语,vn:动名词,adj:形容词,adv:副词)\n" +
            "\n" +
            "3.专利关键接口\n" +
            "name:getKeyWordByAppNo\n" +
            "appNo:申请号\n" +
            "topn:查询多少笔数据\n" +
            "pos:默认n|vn|ns|vn(v:动词,n:名词,ns:名词短语,vn:动名词,adj:形容词,adv:副词)\n" +
            "\n" +
            "4.专利相似度总览接口\n" +
            "name:getPatentSimilarity\n" +
            "appNo:申请号\n" +
            "\n" +
            "5.专利相似度详情接口\n" +
            "name:getSimilarityInfo\n" +
            "appNo:申请号\n" +
            "pageIndex:当前第几页\n" +
            "pageSize:每页显示多少笔数据\n" +
            "\n" +
            "6.专利引用总览接口\n" +
            "name:getPatentQuoteNumber\n" +
            "appNo:申请号\n" +
            "\n" +
            "7.专利引用详情接口\n" +
            "name:getPatentQuoteList\n" +
            "appNo:申请号\n" +
            "\n" +
            "8.专家标引接口\n" +
            "name:getExpertRecommendation\n" +
            "assignee:高校\n" +
            "expert:专家\n" +
            "\n" +
            "9.企业标引数据接口\n" +
            "name:getCompanyRecommendData\n" +
            "companyName:企业名称\n" +
            "\n" +
            "10.企业标引数据地区接口\n" +
            "name:getCompanyByRegionRecommendData\n" +
            "companyName:企业名称\n" +
            "region:省/市\n" +
            "\n" +
            "11.企业IPC统计接口\n" +
            "name:getCompanyStatisticsIPC\n" +
            "companyName:企业名称\n" +
            "\n" +
            "12.企业IPC专利详情接口\n" +
            "name:getCompanyDetailIPC\n" +
            "companyName:企业名称\n" +
            "ipc:Ipc值\n" +
            "pageIndex:当前第几页\n" +
            "pageSize:每页显示多少笔数据\n" +
            "\n" +
            "13.企业合并信息\n" +
            "name:getCompanyOnceName\n" +
            "companyName:企业名称\n" +
            "\n" +
            "14.专利权人专利清单接口\n" +
            "name:getAllCompanyPatentList\n" +
            "patentHolderName:专利权人\n" +
            "pageIndex:当前第几页\n" +
            "pageSize:每页显示多少笔数据\n" +
            "\n" +
            "15.专利权人增量专利清单接口\n" +
            "name:getCompanyPatentList\n" +
            "patentHolderName:专利权人\n" +
            "startTime:开始时间，格式必须为yyyy-MM-dd\n" +
            "\n" +
            "16.根据专利号或者申请号查询专利信息\n" +
            "name:getPatentByPatentNo\n" +
            "patentNo:专利号或者申请号\n" +
            "\n", value = "天弓数据查询接口", produces = "application/json")
    public ApiResponse<Object> res(@RequestParam Map<String, Object> params) throws Exception{
        return ok(tiiKongService.invoke(params));
    }

    @GetMapping("/usertoken")
    @ApiOperation(notes = "专利宝获取user_token", value = "专利宝获取user_token", produces = "application/json")
    public ApiResponse<String> getUserToken(@RequestHeader("X-Request-UserId") Long userId) throws Exception{
        return ok(tiiKongService.getUserToken(userId));
    }

    @GetMapping("/scientific")
    @ApiOperation(notes = "获取科研方向占比信息", value = "获取科研方向占比信息", produces = "application/json")
    public ApiResponse<PatentInfo> getScientificInfo(@RequestHeader("X-Request-UserId") Long userId,
                                                     @RequestHeader("X-Request-UserName") String username) throws Exception{
        return ok(tiiKongService.getScientificInfo(userId,username));
    }

    @GetMapping("/similiarExpertsAndCampany")
    @ApiOperation(notes = "获取相似专家列表及企业", value = "获取相似专家列表及企业", produces = "application/json")
    public ApiResponse<PatentInfo> getSimiliarExperts(@RequestHeader("X-Request-UserId") Long userId,
                                                     @RequestHeader("X-Request-UserName") String username) throws Exception{
        return ok(tiiKongService.getSimiliarExperts(userId,username));
    }

    @GetMapping("/competeCoorperateSubjects")
    @ApiOperation(notes = "竞合主体", value = "竞合主体", produces = "application/json")
    public ApiResponse<List> competeCoorperateSubjects(@RequestHeader("X-Request-UserId") Long userId,
                                                       @RequestHeader("X-Request-UserName") String username) throws Exception{
        return ok(tiiKongService.getCompanyRecommendData(userId,username));
    }

    @GetMapping("/companyDevelopDirection")
    @ApiOperation(notes = "企业研发方向", value = "企业研发方向", produces = "application/json")
    public ApiResponse<List> companyDevelopDirection(@RequestHeader("X-Request-UserId") Long userId,
                                                       @RequestHeader("X-Request-UserName") String username) throws Exception{
        return ok(tiiKongService.companyDevelopDirection(userId,username));
    }

}

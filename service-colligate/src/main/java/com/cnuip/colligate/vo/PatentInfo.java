package com.cnuip.colligate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhaozhihui
 * Date: 2018/11/8.
 * Time: 13:57
 */
@Data
@ApiModel(value = "知识产权VO", description = "知识产权VO")
public class PatentInfo {
    @ApiModelProperty(value="权利人", name="ph", dataType="String")
    private String ph;
    @ApiModelProperty(value="发明人", name="pinSplit", dataType="String")
    private String pinSplit;
    @ApiModelProperty(value="发明专利数", name="fmzlCount", dataType="Long")
    private Long fmzlCount;
    @ApiModelProperty(value="实用新型数", name="syxxCount", dataType="Long")
    private Long syxxCount;
    @ApiModelProperty(value="外观专利数", name="wgzlCount", dataType="Long")
    private Long wgzlCount;
    @ApiModelProperty(value="有效专利数", name="yxCount", dataType="Long")
    private Long yxCount;
    @ApiModelProperty(value="无效专利数", name="wxCount", dataType="Long")
    private Long wxCount;
    @ApiModelProperty(value="专利总数", name="count", dataType="Long")
    private Long count;
    @ApiModelProperty(value="评分最高有效专利申请号", name="an", dataType="String")
    private String an;
    @ApiModelProperty(value="名称", name="ti", dataType="String")
    private String ti;
    @ApiModelProperty(value="价值", name="patentValue", dataType="String")
    private String patentValue;
    @ApiModelProperty(value="超越数", name="surpass", dataType="String")
    private String surpass;
    @ApiModelProperty(value="有效专利平均评估值", name="yxPatentValueAvg", dataType="String")
    private String yxPatentValueAvg;
    @ApiModelProperty(value="有效专利价格", name="yxPatentPrice", dataType="String")
    private String yxPatentPrice;
    @ApiModelProperty(value="无效专利价格", name="wxPatentPrice", dataType="String")
    private String wxPatentPrice;
    @ApiModelProperty(value="分类", name="keywords", dataType="List")
    private List keywords;
    @ApiModelProperty(value="分类数量", name="scores", dataType="List")
    private List scores;
    @ApiModelProperty(value="科研方向集合", name="scientificList", dataType="List")
    private List scientificList;
    @ApiModelProperty(value="相似专家数量", name="expertsNum", dataType="String")
    private String expertsNum;
    @ApiModelProperty(value="可产学研合作的公司数量", name="companiesNum", dataType="String")
    private String companiesNum;
    @ApiModelProperty(value="相似专家集合", name="similiarExpertsList", dataType="List")
    private List similiarExpertsList;
    @ApiModelProperty(value="可合作企业", name="cooperationCampanys", dataType="List")
    private List cooperationCampanys;
}

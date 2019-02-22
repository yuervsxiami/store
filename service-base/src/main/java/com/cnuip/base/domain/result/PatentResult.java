package com.cnuip.base.domain.result;

import com.cnuip.base.base.BaseModel;
import com.cnuip.base.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Data
@ApiModel(value = "专利成果表", description = "专利成果表")
public class PatentResult extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;

    /** 成果编号 */
    @ApiModelProperty(value="成果编号", name="no", dataType="String", example="20181209000001")
    private String no;

    /** 标题 */
    @ApiModelProperty(value="标题", name="title", dataType="String")
    private String title;

    /** 简介 */
    @ApiModelProperty(value="简介", name="introduction", dataType="String")
    private String introduction;

    /** 内容 */
    @ApiModelProperty(value="内容", name="content", dataType="String")
    private String content;

    /** 专利情况 */
    @ApiModelProperty(value="专利情况", name="patentContent", dataType="String")
    private String patentContent;

    /** 预览图 */
    @ApiModelProperty(value="预览图", name="imageUrl", dataType="String")
    private String imageUrl;

    /** 成熟度 [ SAMPLE.已有样品 SMALL_TEST.通过小试 PILOT_TEST.通过中试 BATCH_PRODUCTION.可以量产 ] */
    /** PatentResultMaturityEnum */
    @ApiModelProperty(value="成熟度 [ SAMPLE.已有样品 SMALL_TEST.通过小试 PILOT_TEST.通过中试 BATCH_PRODUCTION.可以量产 ]", name="maturity", dataType="String")
    private String maturity;

    /** 项目组ID sys_team.id */
    @ApiModelProperty(value="项目组ID", name="teamId", dataType="Long")
    private Long teamId;

    /** 项目组 sys_team.name */
    @ApiModelProperty(value="项目组", name="teamName", dataType="String")
    private String teamName;

    /** 操作人 mbr_user.id */
    @ApiModelProperty(value="操作人", name="editorId", dataType="Long")
    private Long editorId;

    /** 操作人 mbr_user.username */
    @ApiModelProperty(value="操作人", name="editorName", dataType="String", example="admin")
    private String editorName;

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTime;


    @Override
    public String checkValue(){

        if (this.getOrganizationId() == null){
            return "PATENTRESULT_ORGANIZATION_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getNo())){
            return "PATENTRESULT_NO_CANNOT_NULL";
        }
        if (this.getNo() == null){
            return "PATENTRESULT_NO_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getNo()) > 16){
            return "PATENTRESULT_NO_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getTitle())){
            return "PATENTRESULT_TITLE_CANNOT_NULL";
        }
        if (this.getTitle() == null){
            return "PATENTRESULT_TITLE_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getTitle()) > 255){
            return "PATENTRESULT_TITLE_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getIntroduction()) > 1000){
            return "PATENTRESULT_INTRODUCTION_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getImageUrl())){
            return "PATENTRESULT_IMAGE_URL_CANNOT_NULL";
        }
        if (this.getImageUrl() == null){
            return "PATENTRESULT_IMAGE_URL_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getImageUrl()) > 255){
            return "PATENTRESULT_IMAGE_URL_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getMaturity())){
            return "PATENTRESULT_MATURITY_CANNOT_NULL";
        }
        if (this.getMaturity() == null){
            return "PATENTRESULT_MATURITY_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getTeamName()) > 255){
            return "PATENTRESULT_TEAM_NAME_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public PatentResult setDefaultValue(){


        return this;
    }
}

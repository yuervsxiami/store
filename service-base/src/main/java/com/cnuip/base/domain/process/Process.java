package com.cnuip.base.domain.process;

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
@ApiModel(value = "提案表", description = "提案表")
public class Process extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;

    /** 流程模版ID pro_tmpl_process.id */
    @ApiModelProperty(value="流程模版ID", name="tmplProcessId", dataType="Long")
    private Long tmplProcessId;

    /** 流程类型 [ NORMAL.普通 AND.会签 OR.或签  ] */
    /** ProcessTypeEnum */
    @ApiModelProperty(value="流程类型 [ NORMAL.普通 AND.会签 OR.或签  ]", name="type", dataType="String", example="NORMAL")
    private String type;

    /** 提案编号 */
    @ApiModelProperty(value="提案编号", name="no", dataType="String", example="20181209000001")
    private String no;

    /** 提案名称 */
    @ApiModelProperty(value="提案名称", name="name", dataType="String", example="动力学院专利申请提案")
    private String name;

    /** 申请类别 [ DOMESTIC.国内专利 INTERNATIONAL.国际专利 ] */
    /** ProcessCategoryEnum */
    @ApiModelProperty(value="申请类别 [ DOMESTIC.国内专利 INTERNATIONAL.国际专利 ]", name="category", dataType="String", example="DOMESTIC")
    private String category;

    /** 专利类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ] */
    /** PatentTypeEnum */
    @ApiModelProperty(value="专利类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ]", name="patentType", dataType="String", example="INVENTION")
    private String patentType;

    /** 申请人 */
    @ApiModelProperty(value="申请人", name="pa", dataType="String")
    private String pa;

    /** 发明人 */
    @ApiModelProperty(value="发明人", name="pin", dataType="String")
    private String pin;

    /** 项目来源 [ SELF_MADE.自拟课题 PROJECT.计划项目 HORIZONTAL.横向课题 OTHER.其它 ] */
    /** ProjectSourceEnum */
    @ApiModelProperty(value="项目来源 [ SELF_MADE.自拟课题 PROJECT.计划项目 HORIZONTAL.横向课题 OTHER.其它 ]", name="source", dataType="String", example="DOMESTIC")
    private String source;

    /** 关联项目组ID sys_team.id */
    @ApiModelProperty(value="关联项目组ID", name="teamId", dataType="Long")
    private Long teamId;

    /** 关联项目组名称 sys_team.name */
    @ApiModelProperty(value="关联项目组名称", name="teamName", dataType="String")
    private String teamName;

    /** 提案内容 */
    @ApiModelProperty(value="提案内容", name="content", dataType="String")
    private String content;

    /** 其他信息 */
    @ApiModelProperty(value="其他信息", name="remark", dataType="String")
    private String remark;

    /** 状态 [ EXAMINING.待审核 RUNNING.审核中 FINISHED.已完成 CLOSED.已关闭 UNEXAMINED.审核不通过 ] */
    /** ProcessStateEnum */
    @ApiModelProperty(value="状态 [ EXAMINING.待审核 RUNNING.审核中 FINISHED.已完成 CLOSED.已关闭 UNEXAMINED.审核不通过 ]", name="state", dataType="String", example="EXAMINING")
    private String state;

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
            return "PROCESS_ORGANIZATION_ID_CANNOT_NULL";
        }
        if (this.getTmplProcessId() == null){
            return "PROCESS_TMPL_PROCESS_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getType())){
            return "PROCESS_TYPE_CANNOT_NULL";
        }
        if (this.getType() == null){
            return "PROCESS_TYPE_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getNo())){
            return "PROCESS_NO_CANNOT_NULL";
        }
        if (this.getNo() == null){
            return "PROCESS_NO_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getNo()) > 16){
            return "PROCESS_NO_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getName())){
            return "PROCESS_NAME_CANNOT_NULL";
        }
        if (this.getName() == null){
            return "PROCESS_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getName()) > 255){
            return "PROCESS_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getCategory())){
            return "PROCESS_CATEGORY_CANNOT_NULL";
        }
        if (this.getCategory() == null){
            return "PROCESS_CATEGORY_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getPatentType())){
            return "PROCESS_PATENT_TYPE_CANNOT_NULL";
        }
        if (this.getPatentType() == null){
            return "PROCESS_PATENT_TYPE_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getPa())){
            return "PROCESS_PA_CANNOT_NULL";
        }
        if (this.getPa() == null){
            return "PROCESS_PA_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getPa()) > 500){
            return "PROCESS_PA_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getPin())){
            return "PROCESS_PIN_CANNOT_NULL";
        }
        if (this.getPin() == null){
            return "PROCESS_PIN_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getPin()) > 100){
            return "PROCESS_PIN_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getSource())){
            return "PROCESS_SOURCE_CANNOT_NULL";
        }
        if (this.getSource() == null){
            return "PROCESS_SOURCE_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getTeamName()) > 255){
            return "PROCESS_TEAM_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getContent())){
            return "PROCESS_CONTENT_CANNOT_NULL";
        }
        if (this.getContent() == null){
            return "PROCESS_CONTENT_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getState())){
            return "PROCESS_STATE_CANNOT_NULL";
        }
        if (this.getState() == null){
            return "PROCESS_STATE_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public Process setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getType())){
            this.setType("NORMAL");
        }
        if (StringUtils.isNullOrEmpty(this.getCategory())){
            this.setCategory("DOMESTIC");
        }
        if (StringUtils.isNullOrEmpty(this.getPatentType())){
            this.setPatentType("INVENTION");
        }
        if (StringUtils.isNullOrEmpty(this.getSource())){
            this.setSource("DOMESTIC");
        }
        if (StringUtils.isNullOrEmpty(this.getState())){
            this.setState("EXAMINING");
        }

        return this;
    }
}

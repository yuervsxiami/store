package com.cnuip.base.domain.authorize;

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
@ApiModel(value = "委托合同表", description = "委托合同表")
public class Authorize extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;

    /** 委托编号 */
    @ApiModelProperty(value="委托编号", name="no", dataType="String", example="20181209000001")
    private String no;

    /** 委托期限至 */
    @ApiModelProperty(value="委托期限至", name="endTime", dataType="java.util.Date", example="2020-12-31")
    private java.util.Date endTime;

    /** 委托状态 [ EXAMINING.待审核 EXAMINED.审核通过 UNEXAMINED.审核不通过 CANCELLED.已取消 ] */
    /** AuthorizeStateEnum */
    @ApiModelProperty(value="委托状态 [ EXAMINING.待审核 EXAMINED.审核通过 UNEXAMINED.审核不通过 CANCELLED.已取消 ]", name="state", dataType="String")
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
            return "AUTHORIZE_ORGANIZATION_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getNo())){
            return "AUTHORIZE_NO_CANNOT_NULL";
        }
        if (this.getNo() == null){
            return "AUTHORIZE_NO_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getNo()) > 16){
            return "AUTHORIZE_NO_MAX_LENGTH_ERROR";
        }
        if (this.getEndTime() == null){
            return "AUTHORIZE_END_TIME_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getState())){
            return "AUTHORIZE_STATE_CANNOT_NULL";
        }
        if (this.getState() == null){
            return "AUTHORIZE_STATE_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public Authorize setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getState())){
            this.setState("EXAMINING");
        }

        return this;
    }
}

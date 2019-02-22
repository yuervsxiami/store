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
@ApiModel(value = "成果标签值表", description = "成果标签值表")
public class LabelValue extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** rlt_label.id */
    @ApiModelProperty(value="", name="labelId", dataType="Long")
    private Long labelId;

    /** 值 */
    @ApiModelProperty(value="值", name="value", dataType="String")
    private String value;

    /** 是否删除 [ YES.是 NO.否 ] */
    /** YesNoEnum */
    @ApiModelProperty(value="是否删除 [ YES.是 NO.否 ]", name="isDelete", dataType="String", example="NO")
    private String isDelete;

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

        if (StringUtils.isNullOrEmpty(this.getValue())){
            return "LABELVALUE_VALUE_CANNOT_NULL";
        }
        if (this.getValue() == null){
            return "LABELVALUE_VALUE_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getValue()) > 255){
            return "LABELVALUE_VALUE_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getIsDelete())){
            return "LABELVALUE_IS_DELETE_CANNOT_NULL";
        }
        if (this.getIsDelete() == null){
            return "LABELVALUE_IS_DELETE_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public LabelValue setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getIsDelete())){
            this.setIsDelete("NO");
        }

        return this;
    }
}

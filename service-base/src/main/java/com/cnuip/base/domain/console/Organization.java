package com.cnuip.base.domain.console;

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
@ApiModel(value = "组织表", description = "组织表")
public class Organization extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 名称 */
    @ApiModelProperty(value="名称", name="name", dataType="String", example="南京大学")
    private String name;

    /** 图标 */
    @ApiModelProperty(value="图标", name="logoUrl", dataType="String", example="http://xxx.com/xxx.png")
    private String logoUrl;

    /** 地址 */
    @ApiModelProperty(value="地址", name="address", dataType="String", example="鼓楼区中山路321号")
    private String address;

    /** 联系人 */
    @ApiModelProperty(value="联系人", name="contact", dataType="String", example="李教授")
    private String contact;

    /** 电话 */
    @ApiModelProperty(value="电话", name="phone", dataType="String", example="139xxxxxxxx")
    private String phone;

    /** 曾用名 */
    @ApiModelProperty(value="曾用名", name="usedName", dataType="String", example="中央大学")
    private String usedName;

    /** 科研方向 */
    @ApiModelProperty(value="科研方向", name="direction", dataType="String", example="航天航空,飞机发动机,火星探测器")
    private String direction;

    /** 简介 */
    @ApiModelProperty(value="简介", name="introduction", dataType="String", example="又名南京大学医学院附属鼓楼医院")
    private String introduction;

    /** 荣誉 */
    @ApiModelProperty(value="荣誉", name="honor", dataType="String")
    private String honor;

    /** 省份ID sys_province.id */
    @ApiModelProperty(value="省份ID", name="provinceId", dataType="Long")
    private Long provinceId;

    /** 省份 */
    @ApiModelProperty(value="省份", name="provinceName", dataType="String")
    private String provinceName;

    /** 是否删除 [ YES.是 NO.否 ] */
    /** YesNoEnum */
    @ApiModelProperty(value="是否删除 [ YES.是 NO.否 ]", name="isDelete", dataType="String", example="NO")
    private String isDelete;

    /** 操作人ID mbr_user.id */
    @ApiModelProperty(value="操作人ID", name="editorId", dataType="Long")
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

        if (StringUtils.isNullOrEmpty(this.getName())){
            return "ORGANIZATION_NAME_CANNOT_NULL";
        }
        if (this.getName() == null){
            return "ORGANIZATION_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getName()) > 255){
            return "ORGANIZATION_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getLogoUrl()) > 255){
            return "ORGANIZATION_LOGO_URL_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getAddress()) > 255){
            return "ORGANIZATION_ADDRESS_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getContact()) > 255){
            return "ORGANIZATION_CONTACT_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getPhone()) > 255){
            return "ORGANIZATION_PHONE_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getUsedName()) > 1000){
            return "ORGANIZATION_USED_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getDirection()) > 1000){
            return "ORGANIZATION_DIRECTION_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getIntroduction()) > 2000){
            return "ORGANIZATION_INTRODUCTION_MAX_LENGTH_ERROR";
        }
        if (this.getProvinceId() == null){
            return "ORGANIZATION_PROVINCE_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getProvinceName())){
            return "ORGANIZATION_PROVINCE_NAME_CANNOT_NULL";
        }
        if (this.getProvinceName() == null){
            return "ORGANIZATION_PROVINCE_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getProvinceName()) > 255){
            return "ORGANIZATION_PROVINCE_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getIsDelete())){
            return "ORGANIZATION_IS_DELETE_CANNOT_NULL";
        }
        if (this.getIsDelete() == null){
            return "ORGANIZATION_IS_DELETE_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public Organization setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getIsDelete())){
            this.setIsDelete("NO");
        }

        return this;
    }
}

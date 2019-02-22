package com.cnuip.base.domain.params;

import com.cnuip.base.base.QueryParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "组织表查询实体", description = "组织表查询实体")
public class OrganizationParam extends QueryParam {

    /** 名称 */
    @ApiModelProperty(value="名称", name="name", dataType="String", example="南京大学")
    private String name;
    public void setName(String name) { this.name = name; } 
    public String getName() { return this.name; } 

    /** 图标 */
    @ApiModelProperty(value="图标", name="logoUrl", dataType="String", example="http://xxx.com/xxx.png")
    private String logoUrl;
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; } 
    public String getLogoUrl() { return this.logoUrl; } 

    /** 地址 */
    @ApiModelProperty(value="地址", name="address", dataType="String", example="鼓楼区中山路321号")
    private String address;
    public void setAddress(String address) { this.address = address; } 
    public String getAddress() { return this.address; } 

    /** 联系人 */
    @ApiModelProperty(value="联系人", name="contact", dataType="String", example="李教授")
    private String contact;
    public void setContact(String contact) { this.contact = contact; } 
    public String getContact() { return this.contact; } 

    /** 电话 */
    @ApiModelProperty(value="电话", name="phone", dataType="String", example="139xxxxxxxx")
    private String phone;
    public void setPhone(String phone) { this.phone = phone; } 
    public String getPhone() { return this.phone; } 

    /** 曾用名 */
    @ApiModelProperty(value="曾用名", name="usedName", dataType="String", example="中央大学")
    private String usedName;
    public void setUsedName(String usedName) { this.usedName = usedName; } 
    public String getUsedName() { return this.usedName; } 

    /** 科研方向 */
    @ApiModelProperty(value="科研方向", name="direction", dataType="String", example="航天航空,飞机发动机,火星探测器")
    private String direction;
    public void setDirection(String direction) { this.direction = direction; } 
    public String getDirection() { return this.direction; } 

    /** 简介 */
    @ApiModelProperty(value="简介", name="introduction", dataType="String", example="又名南京大学医学院附属鼓楼医院")
    private String introduction;
    public void setIntroduction(String introduction) { this.introduction = introduction; } 
    public String getIntroduction() { return this.introduction; } 

    /** 省份ID sys_province.id */
    @ApiModelProperty(value="省份ID", name="provinceId", dataType="Long")
    private Long provinceId;
    public void setProvinceId(Long provinceId) { this.provinceId = provinceId; } 
    public Long getProvinceId() { return this.provinceId; } 

    /** 省份 */
    @ApiModelProperty(value="省份", name="provinceName", dataType="String")
    private String provinceName;
    public void setProvinceName(String provinceName) { this.provinceName = provinceName; } 
    public String getProvinceName() { return this.provinceName; } 

    /** 是否删除 [ YES.是 NO.否 ] */
    /** YesNoEnum */
    @ApiModelProperty(value="是否删除 [ YES.是 NO.否 ]", name="isDelete", dataType="String", example="NO")
    private String isDelete;
    public void setIsDelete(String isDelete) { this.isDelete = isDelete; } 
    public String getIsDelete() { return this.isDelete; } 

    /** 操作人ID mbr_user.id */
    @ApiModelProperty(value="操作人ID", name="editorId", dataType="Long")
    private Long editorId;
    public void setEditorId(Long editorId) { this.editorId = editorId; } 
    public Long getEditorId() { return this.editorId; } 

    /** 操作人 mbr_user.username */
    @ApiModelProperty(value="操作人", name="editorName", dataType="String", example="admin")
    private String editorName;
    public void setEditorName(String editorName) { this.editorName = editorName; } 
    public String getEditorName() { return this.editorName; } 

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTimeFrom;
    public void setCreatedTimeFrom(java.util.Date createdTimeFrom) { this.createdTimeFrom = createdTimeFrom; } 
    public java.util.Date getCreatedTimeFrom() { return this.createdTimeFrom; } 
    private java.util.Date createdTimeTo;
    public void setCreatedTimeTo(java.util.Date createdTimeTo) { this.createdTimeTo = createdTimeTo; } 
    public java.util.Date getCreatedTimeTo() { return this.createdTimeTo; } 

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTimeFrom;
    public void setUpdatedTimeFrom(java.util.Date updatedTimeFrom) { this.updatedTimeFrom = updatedTimeFrom; } 
    public java.util.Date getUpdatedTimeFrom() { return this.updatedTimeFrom; } 
    private java.util.Date updatedTimeTo;
    public void setUpdatedTimeTo(java.util.Date updatedTimeTo) { this.updatedTimeTo = updatedTimeTo; } 
    public java.util.Date getUpdatedTimeTo() { return this.updatedTimeTo; } 

}

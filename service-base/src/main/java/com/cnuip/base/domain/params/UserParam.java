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
@ApiModel(value = "用户表查询实体", description = "用户表查询实体")
public class UserParam extends QueryParam {

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;
    public void setOrganizationId(Long organizationId) { this.organizationId = organizationId; } 
    public Long getOrganizationId() { return this.organizationId; } 

    /** 组织 sys_organization.name */
    @ApiModelProperty(value="组织", name="organizationName", dataType="String")
    private String organizationName;
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; } 
    public String getOrganizationName() { return this.organizationName; } 

    /** 用户名 */
    @ApiModelProperty(value="用户名", name="username", dataType="String", example="admin")
    private String username;
    public void setUsername(String username) { this.username = username; } 
    public String getUsername() { return this.username; } 

    /** 姓名 */
    @ApiModelProperty(value="姓名", name="realName", dataType="String", example="张三")
    private String realName;
    public void setRealName(String realName) { this.realName = realName; } 
    public String getRealName() { return this.realName; } 

    /** 昵称 */
    @ApiModelProperty(value="昵称", name="nickName", dataType="String", example="zhangdan")
    private String nickName;
    public void setNickName(String nickName) { this.nickName = nickName; } 
    public String getNickName() { return this.nickName; } 

    /** 手机号 */
    @ApiModelProperty(value="手机号", name="phone", dataType="String", example="18000000000")
    private String phone;
    public void setPhone(String phone) { this.phone = phone; } 
    public String getPhone() { return this.phone; } 

    /** 性别 */
    @ApiModelProperty(value="性别", name="sex", dataType="String", example="男")
    private String sex;
    public void setSex(String sex) { this.sex = sex; } 
    public String getSex() { return this.sex; } 

    /** 民族 */
    @ApiModelProperty(value="民族", name="nation", dataType="String", example="汉族")
    private String nation;
    public void setNation(String nation) { this.nation = nation; } 
    public String getNation() { return this.nation; } 

    /** 籍贯 */
    @ApiModelProperty(value="籍贯", name="nativePlace", dataType="String", example="江苏南京")
    private String nativePlace;
    public void setNativePlace(String nativePlace) { this.nativePlace = nativePlace; } 
    public String getNativePlace() { return this.nativePlace; } 

    /** 身份证号 */
    @ApiModelProperty(value="身份证号", name="idCardNo", dataType="String")
    private String idCardNo;
    public void setIdCardNo(String idCardNo) { this.idCardNo = idCardNo; } 
    public String getIdCardNo() { return this.idCardNo; } 

    /** 出生日期 */
    @ApiModelProperty(value="出生日期", name="birthday", dataType="java.util.Date")
    private java.util.Date birthdayFrom;
    public void setBirthdayFrom(java.util.Date birthdayFrom) { this.birthdayFrom = birthdayFrom; } 
    public java.util.Date getBirthdayFrom() { return this.birthdayFrom; } 
    private java.util.Date birthdayTo;
    public void setBirthdayTo(java.util.Date birthdayTo) { this.birthdayTo = birthdayTo; } 
    public java.util.Date getBirthdayTo() { return this.birthdayTo; } 

    /** 绑定微信号 */
    @ApiModelProperty(value="绑定微信号", name="wchat", dataType="String")
    private String wchat;
    public void setWchat(String wchat) { this.wchat = wchat; } 
    public String getWchat() { return this.wchat; } 

    /** 证件照 */
    @ApiModelProperty(value="证件照", name="imageUrl", dataType="String", example="http://xxx.com/xxx.png")
    private String imageUrl;
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; } 
    public String getImageUrl() { return this.imageUrl; } 

    /** 职称与头衔 */
    @ApiModelProperty(value="职称与头衔", name="title", dataType="String", example="教授")
    private String title;
    public void setTitle(String title) { this.title = title; } 
    public String getTitle() { return this.title; } 

    /** 部门ID sys_department.id */
    @ApiModelProperty(value="部门ID", name="departmentId", dataType="Long")
    private Long departmentId;
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; } 
    public Long getDepartmentId() { return this.departmentId; } 

    /** 部门名称 sys_department.name */
    @ApiModelProperty(value="部门名称", name="departmentName", dataType="String")
    private String departmentName;
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; } 
    public String getDepartmentName() { return this.departmentName; } 

    /** 岗位ID sys_post.id */
    @ApiModelProperty(value="岗位ID", name="postId", dataType="Long")
    private Long postId;
    public void setPostId(Long postId) { this.postId = postId; } 
    public Long getPostId() { return this.postId; } 

    /** 岗位名称 sys_post.name */
    @ApiModelProperty(value="岗位名称", name="postName", dataType="String")
    private String postName;
    public void setPostName(String postName) { this.postName = postName; } 
    public String getPostName() { return this.postName; } 

    /** 职权ID sys_powers.id */
    @ApiModelProperty(value="职权ID", name="powersId", dataType="Long")
    private Long powersId;
    public void setPowersId(Long powersId) { this.powersId = powersId; } 
    public Long getPowersId() { return this.powersId; } 

    /** 职权名称 sys_powers.name */
    @ApiModelProperty(value="职权名称", name="powersName", dataType="String")
    private String powersName;
    public void setPowersName(String powersName) { this.powersName = powersName; } 
    public String getPowersName() { return this.powersName; } 

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

package com.cnuip.base.domain.user;

import com.cnuip.base.base.BaseModel;
import com.cnuip.base.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Data
@ApiModel(value = "用户表", description = "用户表")
public class User extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;

    /** 组织 sys_organization.name */
    @ApiModelProperty(value="组织", name="organizationName", dataType="String")
    private String organizationName;

    /** 用户名 */
    @ApiModelProperty(value="用户名", name="username", dataType="String", example="admin")
    private String username;

    /** 密码 */
    @ApiModelProperty(value="密码", name="password", dataType="String", example="123456")
    @JsonProperty(access = WRITE_ONLY)
    private String password;

    /** 姓名 */
    @ApiModelProperty(value="姓名", name="realName", dataType="String", example="张三")
    private String realName;

    /** 昵称 */
    @ApiModelProperty(value="昵称", name="nickName", dataType="String", example="zhangdan")
    private String nickName;

    /** 手机号 */
    @ApiModelProperty(value="手机号", name="phone", dataType="String", example="18000000000")
    private String phone;

    /** 性别 */
    @ApiModelProperty(value="性别", name="sex", dataType="String", example="男")
    private String sex;

    /** 民族 */
    @ApiModelProperty(value="民族", name="nation", dataType="String", example="汉族")
    private String nation;

    /** 籍贯 */
    @ApiModelProperty(value="籍贯", name="nativePlace", dataType="String", example="江苏南京")
    private String nativePlace;

    /** 身份证号 */
    @ApiModelProperty(value="身份证号", name="idCardNo", dataType="String")
    private String idCardNo;

    /** 出生日期 */
    @ApiModelProperty(value="出生日期", name="birthday", dataType="java.util.Date")
    private java.util.Date birthday;

    /** 绑定微信号 */
    @ApiModelProperty(value="绑定微信号", name="wchat", dataType="String")
    private String wchat;

    /** 证件照 */
    @ApiModelProperty(value="证件照", name="imageUrl", dataType="String", example="http://xxx.com/xxx.png")
    private String imageUrl;

    /** 职称与头衔 */
    @ApiModelProperty(value="职称与头衔", name="title", dataType="String", example="教授")
    private String title;

    /** 部门ID sys_department.id */
    @ApiModelProperty(value="部门ID", name="departmentId", dataType="Long")
    private Long departmentId;

    /** 部门名称 sys_department.name */
    @ApiModelProperty(value="部门名称", name="departmentName", dataType="String")
    private String departmentName;

    /** 岗位ID sys_post.id */
    @ApiModelProperty(value="岗位ID", name="postId", dataType="Long")
    private Long postId;

    /** 岗位名称 sys_post.name */
    @ApiModelProperty(value="岗位名称", name="postName", dataType="String")
    private String postName;

    /** 职权ID sys_powers.id */
    @ApiModelProperty(value="职权ID", name="powersId", dataType="Long")
    private Long powersId;

    /** 职权名称 sys_powers.name */
    @ApiModelProperty(value="职权名称", name="powersName", dataType="String")
    private String powersName;

    /** 科研方向 */
    @ApiModelProperty(value="科研方向", name="direction", dataType="String", example="航天航空,飞机发动机,火星探测器")
    private String direction;

    /** 简介 */
    @ApiModelProperty(value="简介", name="introduction", dataType="String", example="又名南京大学医学院附属鼓楼医院")
    private String introduction;

    /** 荣誉 */
    @ApiModelProperty(value="荣誉", name="honor", dataType="String")
    private String honor;

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

        if (this.getOrganizationId() == null){
            return "USER_ORGANIZATION_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getOrganizationName())){
            return "USER_ORGANIZATION_NAME_CANNOT_NULL";
        }
        if (this.getOrganizationName() == null){
            return "USER_ORGANIZATION_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getOrganizationName()) > 255){
            return "USER_ORGANIZATION_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getUsername())){
            return "USER_USERNAME_CANNOT_NULL";
        }
        if (this.getUsername() == null){
            return "USER_USERNAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getUsername()) > 20){
            return "USER_USERNAME_MAX_LENGTH_ERROR";
        }
//        if (StringUtils.isNullOrEmpty(this.getPassword())){
//            return "USER_PASSWORD_CANNOT_NULL";
//        }
//        if (this.getPassword() == null){
//            return "USER_PASSWORD_CANNOT_NULL";
//        }
//        if (StringUtils.stringCount(this.getPassword()) > 255){
//            return "USER_PASSWORD_MAX_LENGTH_ERROR";
//        }
        if (StringUtils.isNullOrEmpty(this.getRealName())){
            return "USER_REAL_NAME_CANNOT_NULL";
        }
        if (this.getRealName() == null){
            return "USER_REAL_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getRealName()) > 255){
            return "USER_REAL_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getNickName()) > 255){
            return "USER_NICK_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getPhone())){
            return "USER_PHONE_CANNOT_NULL";
        }
        if (this.getPhone() == null){
            return "USER_PHONE_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getPhone()) > 20){
            return "USER_PHONE_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getSex()) > 8){
            return "USER_SEX_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getNation()) > 125){
            return "USER_NATION_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getNativePlace()) > 255){
            return "USER_NATIVE_PLACE_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getIdCardNo()) > 50){
            return "USER_ID_CARD_NO_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getWchat()) > 125){
            return "USER_WCHAT_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getImageUrl()) > 255){
            return "USER_IMAGE_URL_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getTitle()) > 255){
            return "USER_TITLE_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getDepartmentName()) > 255){
            return "USER_DEPARTMENT_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getPostName()) > 255){
            return "USER_POST_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getPowersName()) > 255){
            return "USER_POWERS_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getDirection()) > 1000){
            return "USER_DIRECTION_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getIntroduction()) > 2000){
            return "USER_INTRODUCTION_MAX_LENGTH_ERROR";
        }
        if("admin".equals(this.getUsername())) {
            if (StringUtils.isNullOrEmpty(this.getIsDelete())) {
                return "USER_IS_DELETE_CANNOT_NULL";
            }
            if (this.getIsDelete() == null) {
                return "USER_IS_DELETE_CANNOT_NULL";
            }
        }
        return null;
    }

    @Override
    public User setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getSex())){
            this.setSex("男");
        }
        if (StringUtils.isNullOrEmpty(this.getIsDelete())){
            this.setIsDelete("NO");
        }

        return this;
    }

    public String checkValueForApp(){

        if (this.getOrganizationId() == null){
            return "USER_ORGANIZATION_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getOrganizationName())){
            return "USER_ORGANIZATION_NAME_CANNOT_NULL";
        }
        if (this.getOrganizationName() == null){
            return "USER_ORGANIZATION_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getOrganizationName()) > 255){
            return "USER_ORGANIZATION_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getUsername())){
            return "USER_USERNAME_CANNOT_NULL";
        }
        if (this.getUsername() == null){
            return "USER_USERNAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getUsername()) > 20){
            return "USER_USERNAME_MAX_LENGTH_ERROR";
        }

        if (StringUtils.stringCount(this.getNickName()) > 255){
            return "USER_NICK_NAME_MAX_LENGTH_ERROR";
        }

        if (StringUtils.stringCount(this.getSex()) > 8){
            return "USER_SEX_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getNation()) > 125){
            return "USER_NATION_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getNativePlace()) > 255){
            return "USER_NATIVE_PLACE_MAX_LENGTH_ERROR";
        }

        if (StringUtils.stringCount(this.getImageUrl()) > 255){
            return "USER_IMAGE_URL_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getTitle()) > 255){
            return "USER_TITLE_MAX_LENGTH_ERROR";
        }

        if (StringUtils.stringCount(this.getIntroduction()) > 2000){
            return "USER_INTRODUCTION_MAX_LENGTH_ERROR";
        }

        return null;
    }

}

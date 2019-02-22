package com.cnuip.user.vo.domain;

import com.cnuip.base.base.BaseModel;
import lombok.Data;

/**
 * Created by wangzhibin on 2018/3/14.
 */

@Data
public class Shop extends BaseModel {

    /** ID系统自动生成 */
    private Long id;

    /** 申请人 */
    private String pa;

    /** 手机号 */
    private String phone;

    /** 使用人姓名 */
    private String realName;

    /** 身份证号码 */
    private String idCardNo;

    /** 店铺名称 */
    private String name;

    /** 佣金比例,仅用于该店铺委托中高自营专利的利润分配，如区域分平台为自营，请设置为0 */
    private Long commisionPercent;

    /** 服务费 */
    private java.math.BigDecimal serviceCharge;

    /** 有效日期(开始) */
    private java.util.Date startTime;

    /** 有效日期(结束) */
    private java.util.Date endTime;

    /** shp_shop_type.id */
    private Long shopTypeId;

    /** sys_site.id 归属平台 */
    private Long siteId;

    /** sys_site.name(归属平台) */
    private String siteName;

    /** 店铺简介 */
    private String introduction;

    /** 关注度 */
    private Long attention;

    /** 缩略图 */
    private String imageUrl;

    /** mbr_user.id */
    private Long userId;

    /** mbr_user.name */
    private String username;

    /** mbr_user.password */
    private String password;

    /** 状态(EXAMINING.待审核 UNEXAMINED.审核不通过 TO_BE_OPERATED.待运营 IN_OPERATION.运营中 EXPIRED.已过期) */
    private String state;

    /** 是否删除(YES.是 NO.否) */
    private String isDelete;

    /** 数据同步key */
    private String remoteKey;

    /**  */
    private java.util.Date createdTime;

    /**  */
    private java.util.Date updatedTime;

    /** 地址 */
    private String address;

    /** 科研方向 */
    private String direction;

    /** 荣誉 */
    private String honor;

    /** 职称与头衔 */
    private String title;

    /** 承接课题 */
    private String topic;

    /** 科技成果 */
    private String achievements;

}

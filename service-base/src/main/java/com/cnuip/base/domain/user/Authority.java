package com.cnuip.base.domain.user;

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
@ApiModel(value = "权限表", description = "权限表")
public class Authority extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 权限类型 [ MENU.菜单 BUTTON.按钮 TAB.页夹 ] */
    /** AuthorityTypeEnum */
    @ApiModelProperty(value="权限类型 [ MENU.菜单 BUTTON.按钮 TAB.页夹 ]", name="type", dataType="String", example="MENU")
    private String type;

    /** 名称 */
    @ApiModelProperty(value="名称", name="name", dataType="String", example="AUTH_WELCOME")
    private String name;

    /** 标题 */
    @ApiModelProperty(value="标题", name="title", dataType="String", example="欢迎页")
    private String title;

    /** 父级ID */
    @ApiModelProperty(value="父级ID", name="parentId", dataType="Long")
    private Long parentId;

    /** 序号 */
    @ApiModelProperty(value="序号", name="sortOrder", dataType="Integer")
    private Integer sortOrder;

    /** 路径 */
    @ApiModelProperty(value="路径", name="url", dataType="String")
    private String url;

    /** 图标 */
    @ApiModelProperty(value="图标", name="icon", dataType="String")
    private String icon;

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

        if (StringUtils.isNullOrEmpty(this.getType())){
            return "AUTHORITY_TYPE_CANNOT_NULL";
        }
        if (this.getType() == null){
            return "AUTHORITY_TYPE_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getName())){
            return "AUTHORITY_NAME_CANNOT_NULL";
        }
        if (this.getName() == null){
            return "AUTHORITY_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getName()) > 255){
            return "AUTHORITY_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getTitle())){
            return "AUTHORITY_TITLE_CANNOT_NULL";
        }
        if (this.getTitle() == null){
            return "AUTHORITY_TITLE_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getTitle()) > 255){
            return "AUTHORITY_TITLE_MAX_LENGTH_ERROR";
        }
        if (this.getSortOrder() == null){
            return "AUTHORITY_SORT_ORDER_CANNOT_NULL";
        }
        if (this.getSortOrder() > 99999 || this.getSortOrder() < 0){
            return "AUTHORITY_SORT_ORDER_MIN_MAX_VALUE_ERROR";
        }
        if (StringUtils.stringCount(this.getUrl()) > 255){
            return "AUTHORITY_URL_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getIcon()) > 255){
            return "AUTHORITY_ICON_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public Authority setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getType())){
            this.setType("MENU");
        }
        if (this.getSortOrder() == null){
            this.setSortOrder(0);
        }

        return this;
    }
}

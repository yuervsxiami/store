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
@ApiModel(value = "权限表查询实体", description = "权限表查询实体")
public class AuthorityParam extends QueryParam {

    /** 权限类型 [ MENU.菜单 BUTTON.按钮 TAB.页夹 ] */
    /** AuthorityTypeEnum */
    @ApiModelProperty(value="权限类型 [ MENU.菜单 BUTTON.按钮 TAB.页夹 ]", name="type", dataType="String", example="MENU")
    private String type;
    public void setType(String type) { this.type = type; } 
    public String getType() { return this.type; } 

    /** 名称 */
    @ApiModelProperty(value="名称", name="name", dataType="String", example="AUTH_WELCOME")
    private String name;
    public void setName(String name) { this.name = name; } 
    public String getName() { return this.name; } 

    /** 标题 */
    @ApiModelProperty(value="标题", name="title", dataType="String", example="欢迎页")
    private String title;
    public void setTitle(String title) { this.title = title; } 
    public String getTitle() { return this.title; } 

    /** 父级ID */
    @ApiModelProperty(value="父级ID", name="parentId", dataType="Long")
    private Long parentId;
    public void setParentId(Long parentId) { this.parentId = parentId; } 
    public Long getParentId() { return this.parentId; } 

    /** 序号 */
    @ApiModelProperty(value="序号", name="sortOrder", dataType="Integer")
    private Integer sortOrderFrom;
    public void setSortOrderFrom(Integer sortOrderFrom) { this.sortOrderFrom = sortOrderFrom; } 
    public Integer getSortOrderFrom() { return this.sortOrderFrom; } 
    private Integer sortOrderTo;
    public void setSortOrderTo(Integer sortOrderTo) { this.sortOrderTo = sortOrderTo; } 
    public Integer getSortOrderTo() { return this.sortOrderTo; } 

    /** 路径 */
    @ApiModelProperty(value="路径", name="url", dataType="String")
    private String url;
    public void setUrl(String url) { this.url = url; } 
    public String getUrl() { return this.url; } 

    /** 图标 */
    @ApiModelProperty(value="图标", name="icon", dataType="String")
    private String icon;
    public void setIcon(String icon) { this.icon = icon; } 
    public String getIcon() { return this.icon; } 

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

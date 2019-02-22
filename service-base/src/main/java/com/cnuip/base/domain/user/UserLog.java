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
@ApiModel(value = "操作日志", description = "操作日志")
public class UserLog extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** mbr_user.id */
    @ApiModelProperty(value="", name="userId", dataType="Long")
    private Long userId;

    /** 用户名 mbr_user.username */
    @ApiModelProperty(value="用户名", name="username", dataType="String", example="admin")
    private String username;

    /** 内容 */
    @ApiModelProperty(value="内容", name="content", dataType="String", example="操作内容")
    private String content;

    /** 操作时间 */
    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;


    @Override
    public String checkValue(){

        if (StringUtils.isNullOrEmpty(this.getUsername())){
            return "USERLOG_USER_NAME_CANNOT_NULL";
        }
        if (this.getUsername() == null){
            return "USERLOG_USER_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getUsername()) > 255){
            return "USERLOG_USER_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getContent())){
            return "USERLOG_CONTENT_CANNOT_NULL";
        }
        if (this.getContent() == null){
            return "USERLOG_CONTENT_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getContent()) > 2000){
            return "USERLOG_CONTENT_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public UserLog setDefaultValue(){


        return this;
    }
}

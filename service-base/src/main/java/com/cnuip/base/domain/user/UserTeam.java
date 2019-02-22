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
@ApiModel(value = "用户项目组表", description = "用户项目组表")
public class UserTeam extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 项目组ID sys_team.id */
    @ApiModelProperty(value="项目组ID", name="teamId", dataType="Long")
    private Long teamId;

    /** 项目组 sys_team.name */
    @ApiModelProperty(value="项目组", name="teamName", dataType="String")
    private String teamName;

    /** 用户ID mbr_user.id */
    @ApiModelProperty(value="用户ID", name="userId", dataType="Long")
    private Long userId;


    @Override
    public String checkValue(){

        if (this.getTeamId() == null){
            return "USERTEAM_TEAM_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getTeamName())){
            return "USERTEAM_TEAM_NAME_CANNOT_NULL";
        }
        if (this.getTeamName() == null){
            return "USERTEAM_TEAM_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getTeamName()) > 255){
            return "USERTEAM_TEAM_NAME_MAX_LENGTH_ERROR";
        }
        if (this.getUserId() == null){
            return "USERTEAM_USER_ID_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public UserTeam setDefaultValue(){


        return this;
    }
}

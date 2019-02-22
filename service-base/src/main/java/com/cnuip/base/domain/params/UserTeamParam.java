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
@ApiModel(value = "用户项目组表查询实体", description = "用户项目组表查询实体")
public class UserTeamParam extends QueryParam {

    /** 项目组ID sys_team.id */
    @ApiModelProperty(value="项目组ID", name="teamId", dataType="Long")
    private Long teamId;
    public void setTeamId(Long teamId) { this.teamId = teamId; } 
    public Long getTeamId() { return this.teamId; } 

    /** 项目组 sys_team.name */
    @ApiModelProperty(value="项目组", name="teamName", dataType="String")
    private String teamName;
    public void setTeamName(String teamName) { this.teamName = teamName; } 
    public String getTeamName() { return this.teamName; } 

    /** 用户ID mbr_user.id */
    @ApiModelProperty(value="用户ID", name="userId", dataType="Long")
    private Long userId;
    public void setUserId(Long userId) { this.userId = userId; } 
    public Long getUserId() { return this.userId; } 

}

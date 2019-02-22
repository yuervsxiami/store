package com.cnuip.console.rest;

import com.cnuip.base.domain.console.Team;
import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.TeamParam;
import com.cnuip.console.service.TeamService;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/team")
@Api(value = "TeamController", description = "项目组接口")
public class TeamController extends ControllerResponse {

    @Autowired
    private TeamService teamService;

    @PostMapping("/")
    @ApiOperation(notes = "新增项目组", value = "新增项目组", produces = "application/json")
    public ApiResponse<Team> add(@RequestHeader("X-Request-UserId") Long editorId,
                                 @RequestHeader("X-Request-UserName") String editorName,
                                 @RequestHeader("X-Request-OrganizationId") Long orgId,
                                 @RequestBody Team team) throws Exception {
        team.setEditorId(editorId);
        team.setEditorName(editorName);
        team.setOrganizationId(orgId);
        return ok(teamService.addTeam(team));
    }


    @PutMapping("/")
    @ApiOperation(notes = "修改项目组", value = "修改项目组", produces = "application/json")
    public ApiResponse<Team> update(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                    @RequestBody Team team) throws Exception {
        if (team.getOrganizationId() == null) {
            team.setOrganizationId(orgId);
        }
        return ok(teamService.update(team));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除项目组", value = "删除项目组", produces = "application/json")
    public ApiResponse<Team> delete(@RequestParam("id") Long id) throws Exception {
        return ok(teamService.updateFieldByKey(id, "isDelete", YesNoEnum.YES));
    }

    @GetMapping("/")
    @ApiOperation(notes = "项目组列表", value = "项目组列表", produces = "application/json")
    public ApiResponse<PageInfo<Team>> search( @RequestHeader("X-Request-OrganizationId") Long orgId,
                                              TeamParam teamParam) throws Exception {
        teamParam.setOrganizationId(orgId);
        return ok(teamService.selectMany(teamParam));
    }

    @GetMapping("/{id}")
    @ApiOperation(notes = "获取项目组", value = "获取项目组", produces = "application/json")
    public ApiResponse<Team> searchById( @PathVariable(value = "id") Long id) throws Exception {
        return ok(teamService.selectOneByKey(id));
    }
}

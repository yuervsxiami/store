package com.cnuip.console.rest;

import com.cnuip.base.domain.console.Post;
import com.cnuip.base.domain.params.PostParam;
import com.cnuip.console.service.PostService;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.gaea.service.web.ControllerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/post")
@Api(value = "PostController", description = "岗位接口")
public class PostController extends ControllerResponse {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    @ApiOperation(notes = "新增岗位", value = "新增岗位", produces = "application/json")
    public ApiResponse<Post> add(@RequestHeader("X-Request-UserId") Long editorId,
                                 @RequestHeader("X-Request-UserName") String editorName,
                                 @RequestHeader("X-Request-OrganizationId") Long orgId,
                                 @RequestBody Post post) throws Exception {
        post.setEditorId(editorId);
        post.setEditorName(editorName);
        post.setOrganizationId(orgId);
        return ok(postService.addPost(post));
    }


    @PutMapping("/")
    @ApiOperation(notes = "修改岗位", value = "修改岗位", produces = "application/json")
    public ApiResponse<Post> update(@RequestHeader("X-Request-OrganizationId") Long orgId,
                                    @RequestBody Post post) throws Exception {
        if (post.getOrganizationId() == null) {
            post.setOrganizationId(orgId);
        }
        return ok(postService.updatePost(post));
    }

    @DeleteMapping("/")
    @ApiOperation(notes = "删除岗位", value = "删除岗位", produces = "application/json")
    public ApiResponse<Post> delete(@RequestParam("id") Long id) throws Exception {
        return ok(postService.deletePost(id));
    }

    @GetMapping("/")
    @ApiOperation(notes = "岗位列表", value = "岗位列表", produces = "application/json")
    public ApiResponse<List<Post>> search(@RequestHeader("X-Request-OrganizationId") Long orgId,PostParam postParam) throws Exception {
        postParam.setOrganizationId(orgId);
        return ok(postService.getAll(postParam));
    }
}

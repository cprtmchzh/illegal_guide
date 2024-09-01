package com.department.guide.domain.post

import com.department.guide.domain.auth.CustomUser
import com.department.guide.global.httpStatus.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@Tag(name = "post-controller", description = "게시글 관련 API")
@RequestMapping("/api/posts")
@RestController
class PostController(
    private val postService: PostService
) {

    @Operation(summary = "게시글 리스트 조회", description = "작성된 게시글 전체 조회")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "게시글 리스트 조회 성공")
    ])
    @GetMapping("")
    fun getPostList(@RequestParam(value = "page") page: Int,
                    @RequestParam(value = "kw", defaultValue = "") keyword: String):
            BaseResponse<Page<PostResponse>> {

        val paging: Page<PostResponse>? = this.postService.getPostList(page, keyword)
        return BaseResponse(data = paging, message = "게시글 리스트 조회 성공")
    }

    @Operation(summary = "게시글 조회", description = "선택한 특정 게시글 조회")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "게시글 조회 성공")
    ])
    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long):
            BaseResponse<Optional<Post>> {

        return BaseResponse(data = postService.getPost(postId), message = "게시글 조회 성공")
    }

    @Operation(summary = "게시글 생성", description = "게시글 작성 및 DB 저장")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "게시글 작성 성공")
    ])
    @PostMapping("/create")
    fun createPost(@RequestBody @Valid postRequest: PostRequest):
            BaseResponse<Unit> {

        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser).userId

        postService.createPost(postRequest, userId)
        return BaseResponse(message = "게시글 생성 성공")
    }

    @Operation(summary = "게시글 삭제", description = "게시글 및 관련 댓글 삭제")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "게시글 삭제 성공")
    ])
    @PostMapping("/delete/{postId}")
    fun deletePost(@PathVariable postId: Long):
            BaseResponse<Unit> {

        postService.deletePost(postId)
        return BaseResponse(message = "게시글 삭제 성공")
    }
}
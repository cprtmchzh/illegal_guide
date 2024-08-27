package com.department.guide.domain.post.comment

import com.department.guide.domain.auth.CustomUser
import com.department.guide.global.httpStatus.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@Tag(name = "post-controller", description = "게시글 관련 API")
@RequestMapping("/api/posts")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @Operation(summary = "댓글 등록", description = "게시글에 댓글 작성")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "댓글 등록 성공")
    ])
    @PostMapping("/{postId}/createComment")
    fun createComment(@PathVariable postId: Long,
                      @RequestBody @Valid commentRequest: CommentRequest):
            BaseResponse<Unit> {

        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser).userId

        commentService.createComment(commentRequest, postId, userId)
        return BaseResponse(message = "댓글 등록 성공")
    }

    @Operation(summary = "댓글 삭제", description = "게시글에 등록된 댓글 삭제")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "댓글 삭제 성공")
    ])
    @PostMapping("/deleteComment/{commentId}")
    fun deleteComment(@PathVariable commentId: Long):
            BaseResponse<Unit> {

        commentService.deleteComment(commentId)
        return BaseResponse(message = "댓글 삭제 성공")
    }
}
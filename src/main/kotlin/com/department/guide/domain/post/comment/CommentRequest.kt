package com.department.guide.domain.post.comment

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "게시물 댓글 생성 DTO")
data class CommentRequest (

    @Schema(hidden = true)
    val id: Long?,

    @Schema(description = "댓글 내용", example = "안됨 ㄹㅇㅋㅋ")
    @field:NotBlank
    @JsonProperty("content")
    private val _content: String?
) {

    val content: String
        get() = _content!!
}
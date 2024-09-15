package com.department.guide.domain.post

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(description = "게시물 생성 DTO")
data class PostRequest (

    @Schema(hidden = true)
    val id: Long?,

    @Schema(description = "게시글 제목", example = "휴학 관련 질문")
    @field:NotBlank
    @Size(min = 5, max = 50)
    @JsonProperty("title")
    private val _title: String?,

    @Schema(description = "게시글 내용", example = "간장공장공장장은 강공장공장장")
    @field:NotBlank
    @Size(min = 1, max = 500)
    @JsonProperty("content")
    private val _content: String?,
) {

    val title: String
        get() = _title!!

    val content: String
        get() = _content!!
}
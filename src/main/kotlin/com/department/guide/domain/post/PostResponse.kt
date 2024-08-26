package com.department.guide.domain.post

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "게시물 리스트 응답 DTO")
data class PostResponse (

    @Schema(description = "게시물 ID", example = "2")
    val id: Long,

    @Schema(description = "게시물 제목", example = "졸업 요건 질문")
    val title: String,

    @Schema(description = "게시물 작성 날짜", example = "2024.02.22")
    val hits: Long,

    @Schema(description = "게시물 조회수", example = "12")
    val timestamp: LocalDateTime
)
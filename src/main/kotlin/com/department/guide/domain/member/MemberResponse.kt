package com.department.guide.domain.member

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원정보 조회 응답 DTO")
data class MemberResponse (

    @Schema(description = "유저 ID", example = "2")
    val id: Long,

    @Schema(description = "이메일", example = "cprtmchzh4086@gmail.com")
    val email: String,

    @Schema(description = "닉네임", example = "보노보노")
    val nickname: String,
)
package com.department.guide.domain.member

import com.department.guide.domain.auth.CustomUser
import com.department.guide.global.httpStatus.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "member-controller", description = "유저 관련 API")
@RequestMapping("/api/members")
@RestController
class MemberController(
    private val memberService: MemberService
) {

    @Operation(summary = "회원 정보 조회", description = "해당 회원 정보 조회")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "정보 조회 성공")
    ])
    @GetMapping("/info")
    fun searchMemberInfo(): BaseResponse<MemberResponse> {

        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .userId
        val response = memberService.searchMemberInfo(userId)

        return BaseResponse(data = response, message = "유저 정보 조회 성공")
    }
}
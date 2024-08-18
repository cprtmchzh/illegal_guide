package com.department.guide.domain.auth

import com.department.guide.domain.member.MemberRequest
import com.department.guide.domain.member.MemberResponse
import com.department.guide.global.httpStatus.BaseResponse
import com.department.guide.global.jwt.TokenInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "auth-controller", description = "인증 관련 API")
@RequestMapping("/api/auths")
@RestController
class AuthController (
    private val authService: AuthService
) {

    @Operation(summary = "회원가입", description = "회원가입 전송")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "회원가입 성공")
    ])
    @PostMapping("/join")
    fun join(@RequestBody @Valid memberRequest: MemberRequest):
            BaseResponse<Unit> {

        val resultMsg: String = authService.join(memberRequest)
        return BaseResponse(message = resultMsg)
    }

    @Operation(summary = "로그인", description = "로그인 전송")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "로그인 성공")
    ])
    @PostMapping("/login")
    fun login(@RequestBody @Valid authRequest: AuthRequest):
            BaseResponse<TokenInfo> {

        val tokenInfo = authService.login(authRequest)
        return BaseResponse(data = tokenInfo, message = "로그인에 성공하였습니다.")
    }
}
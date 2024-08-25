package com.department.guide.domain.auth

import com.department.guide.domain.member.MemberRequest
import com.department.guide.global.httpStatus.BaseResponse
import com.department.guide.global.jwt.JwtTokenProvider
import com.department.guide.global.jwt.TokenInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "auth-controller", description = "인증 관련 API")
@RequestMapping("/api/auths")
@RestController
class AuthController (
    private val authService: AuthService,
    private val jwtTokenProvider: JwtTokenProvider
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
            ResponseEntity<String> {

        val tokenInfo = authService.login(authRequest)

        return ResponseEntity.ok()
            .header("Authorization", "Bearer " + tokenInfo.accessToken).build()
    }

    @Operation(summary = "토큰 리프레쉬", description = "Access Token과 Refresh Token을 비교 후 토큰 재발급")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "토큰 재발급 성공")
    ])
    @PostMapping("/refresh")
    fun refreshToken(request: HttpServletRequest):
            ResponseEntity<String> {

        val accessToken = request.getHeader("Authorization").substring(7)

        if (!jwtTokenProvider.tokenMatches(accessToken)) {
            return ResponseEntity.badRequest().body("두 토큰의 정보가 일치하지 않습니다.")
        }

        return ResponseEntity.ok()
            .header("Authorization", "Bearer " +
                    authService.regenerateToken(accessToken)).build();
    }
}
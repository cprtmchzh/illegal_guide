package com.department.guide.domain.auth.mail

import com.department.guide.domain.member.MemberService
import com.department.guide.global.httpStatus.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "mail-controller", description = "메일 전송 관련 API")
@RestController
@RequestMapping("/api/emails")
class MailController(
    private val memberService: MemberService
) {

    @Operation(summary = "인증 메일 전송", description = "이메일 인증을 위한 메일 전송")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "메일 전송 성공")
    ])
    @PostMapping("/verification-requests")
    fun sendMessage(@RequestParam("email") @Valid email: String):
            BaseResponse<Unit> {

        memberService.sendCodeToEmail(email)
        return BaseResponse(message = "메일을 전송하였습니다.")
    }

    @Operation(summary = "인증 메일 검증", description = "요청한 인증번호가 일치하는지 확인")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "메일 검증 성공")
    ])
    @GetMapping("verification")
    fun verificationEmail(@RequestParam("email") @Valid email: String,
                          @RequestParam("code") authCode: String):
            BaseResponse<Unit> {

        val response = memberService.verifiedCode(email, authCode)
        if (response) {
            return BaseResponse(message = "인증에 성공하였습니다.")
        }
        else
            throw Exception("인증 번호가 일치하지 않습니다.")
    }
}
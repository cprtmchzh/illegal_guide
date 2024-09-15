package com.department.guide.domain.member

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Schema(description = "회원가입 전송 DTO")
data class MemberRequest (

    @Schema(hidden = true)
    val id: Long?,

    @Schema(description = "이메일", example = "cprtmchzh4086@gmail.com")
    @field:NotBlank
    @field:Email
    @Size(min = 1, max = 30)
    @JsonProperty("email")
    private val _email: String?,

    @field:Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$")
    @Schema(description = "패스워드", example = "tjsdif1231@#")
    @field:NotBlank
    @Size(min = 8, max = 20)
    @JsonProperty("password")
    private val _password: String?,

    @field:Pattern(regexp = "^[a-zA-Z]{1,12}\$")
    @Schema(description = "닉네임", example = "qls12")
    @Size(min = 3, max = 10)
    @field:NotBlank
    @JsonProperty("nickname")
    private val _nickname: String?,
) {

    val email: String
        get() = _email!!

    val password: String
        get() = _password!!

    val nickname: String
        get() = _nickname!!
}
package com.department.guide.domain.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

@Schema(description = "로그인 전송 DTO")
data class AuthRequest (

    @Schema(description = "이메일", example = "cprtmchzh4086@gmail.com")
    @field:NotBlank
    @field:Email
    @JsonProperty("email")
    private val _email: String?,

    @field:Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$")
    @Schema(description = "패스워드", example = "tjsdif1231@#")
    @field:NotBlank
    @JsonProperty("password")
    private val _password: String?,
) {

    val email: String
        get() = _email!!

    val password: String
        get() = _password!!
}
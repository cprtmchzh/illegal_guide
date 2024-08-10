package com.department.guide.domain.member

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class MemberDtoRequest (

    val id: Long?,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$")
    val password: String,

    @field:NotBlank
    @field:Pattern(regexp = "^[a-zA-Z]{1,12}\$")
    val nickname: String,
)
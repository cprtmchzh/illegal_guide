package com.department.guide.global.jwt

import io.swagger.v3.oas.annotations.media.Schema

data class TokenInfo (

    @Schema(description = "암호화 방식", example = "bearer")
    val grantType: String,

    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaW" +
            "41NDUxMTU2M0BnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV8kKGl0LnJvbGUpIiwidXNlcklkIjo" +
            "rRzjKqzd-dtAUZef7JA")
    val accessToken: String,

    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaW" +
            "xLCJpYXQiOjE3MjM5NDk2MDQsImV4cCI6MTcyMzk5MjgwNH0.AlUIAqCVkA_jQIeQkEhOyxQW" +
            "rRzjKqzd-dtAUZef7JA")
    val refreshToken: String,
)
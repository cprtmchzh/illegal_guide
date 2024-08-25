package com.department.guide.global.jwt

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "RefreshToken", timeToLive = 60 * 60 * 1000L)
class RefreshToken (

    @Id
    val email: String,

    val token: String
    )
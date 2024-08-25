package com.department.guide.domain.auth.mail

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "AuthCode", timeToLive = 60 * 1000L)
class Mail (

    @Id
    val email: String,

    val authCode: String
)
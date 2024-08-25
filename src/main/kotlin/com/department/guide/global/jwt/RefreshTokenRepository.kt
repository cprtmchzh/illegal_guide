package com.department.guide.global.jwt

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefreshTokenRepository : JpaRepository<RefreshToken, String> {

    fun findByEmail(email: String) : RefreshToken
}
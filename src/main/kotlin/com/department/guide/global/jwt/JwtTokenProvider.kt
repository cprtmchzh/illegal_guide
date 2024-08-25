package com.department.guide.global.jwt

import com.department.guide.domain.auth.CustomUser
import com.department.guide.domain.member.ROLE
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

const val accessTokenValidationTime: Long = 60 * 60 * 1000L // 1시간
const val refreshTokenValidationTime: Long = 24 * 60 * 60 * 1000L // 24시간

@Component
class JwtTokenProvider(
    private val refreshTokenRepository: RefreshTokenRepository
) {

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    private val key by lazy {
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    fun createToken(authentication: Authentication): TokenInfo {

        val authorities: String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)

        val now = Date()
        val accessExpiration = Date(now.time + accessTokenValidationTime)
        val refreshExpiration = Date(now.time + refreshTokenValidationTime)

        val accessToken = Jwts.builder()
            .setSubject(authentication.name)
            .claim("auth", authorities)
            .claim("userId", (authentication.principal as CustomUser).userId)
            .setIssuedAt(now)
            .setExpiration(accessExpiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        val refreshToken = Jwts.builder()
            .setSubject(authentication.name)
            .claim("userId", (authentication.principal as CustomUser).userId)
            .setExpiration(refreshExpiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return TokenInfo("Bearer", accessToken, refreshToken)
    }

    fun getAuthentication(accessToken: String): Authentication {

        val claims: Claims = getClaims(accessToken)
        val auth = claims["auth"]?: throw RuntimeException("권한 정보가 없는 토큰입니다.")
        val userId = claims["userId"] ?: throw RuntimeException("유저 정보가 없는 토큰입니다.")

        val authorities: Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        val principal: UserDetails =
            CustomUser(userId.toString().toLong(), claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    fun tokenMatches(accessToken: String): Boolean {

        val accessTokenClaim: Claims = getClaims(accessToken)
        val email: String =
            (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser).username

        val refreshTokenClaim: Claims =
            getClaims(refreshTokenRepository.findByEmail(email).token)

        if (accessTokenClaim.subject.equals(refreshTokenClaim.subject))
            return true;

        return false
    }

    fun recreationAccessToken(userId: Long, email: String, roles: ROLE): String {

        val claims: Claims = Jwts.claims().setSubject(email)
        claims.put("userId", userId)
        claims.put("auth", roles)

        val now = Date()
        val accessExpiration = Date(now.time + accessTokenValidationTime)

        val accessToken = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(accessExpiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return accessToken;
    }

    fun validateToken(token: String): Boolean {

        try {
            getClaims(token)
            return true
        } catch (e: Exception) {

            when (e) {
                is SecurityException -> {} // Invalid JWT Token
                is MalformedJwtException -> {} // Invalid JWT Token
                is ExpiredJwtException -> {} // Expired JWT Token
                is UnsupportedJwtException -> {} // Unsupported JWT Token
                is IllegalArgumentException -> {} // JWT claims string is empty
                else -> {} // else
            }
            println(e.message)
        }
        return false
    }

    private fun getClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
}
package com.department.guide.domain.auth;

import com.department.guide.domain.member.Member
import com.department.guide.domain.member.MemberRequest
import com.department.guide.domain.member.MemberRepository
import com.department.guide.domain.member.ROLE
import com.department.guide.global.exception.InvalidInputException
import com.department.guide.global.jwt.JwtTokenProvider
import com.department.guide.global.jwt.RefreshToken
import com.department.guide.global.jwt.RefreshTokenRepository
import com.department.guide.global.jwt.TokenInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.transaction.Transactional;
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service;
import java.util.*

@Service
@Transactional
class AuthService ( /* ㄹㅇㅋㅋ */
    private val memberRepository: MemberRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun join(memberRequest: MemberRequest): String {

        var member: Member? = memberRepository.findByEmail(memberRequest.email)

        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        member = Member(
            null,
            memberRequest.email,
            passwordEncoder.encode(memberRequest.password),
            memberRequest.nickname,
            ROLE.MEMBER
        )

        memberRepository.save(member)

        return "회원 가입이 완료되었습니다."
    }

    fun login(authRequest: AuthRequest): TokenInfo {

        val authenticationToken =
            UsernamePasswordAuthenticationToken(authRequest.email, authRequest.password)
        val authentication =
            authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return createToken(jwtTokenProvider.createToken(authentication), authRequest.email);
    }

    fun createToken(tokenInfo: TokenInfo, email: String): TokenInfo {

        val refreshToken = RefreshToken(
            email,
            tokenInfo.refreshToken
        )

        refreshTokenRepository.save(refreshToken)
        return tokenInfo;
    }

    fun regenerateToken(accessToken: String): String {

        val memberId: Long =
            (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser).userId

        val member: Optional<Member> = memberRepository.findById(memberId)
        return jwtTokenProvider.recreationAccessToken(member.get().id!!, member.get().email, member.get().role)
    }
}
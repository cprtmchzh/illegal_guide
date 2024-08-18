package com.department.guide.domain.auth

import com.department.guide.domain.member.Member
import com.department.guide.domain.member.MemberRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        memberRepository.findByEmail(username)
            ?.let { createUserDetails(it) }
            ?: throw UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.")

    private fun createUserDetails(member: Member): UserDetails =
        CustomUser(
            member.id!!,
            member.email,
            member.password,
            member.role.toString().map {
                SimpleGrantedAuthority("ROLE_" + member.role.toString()) }
        )
}
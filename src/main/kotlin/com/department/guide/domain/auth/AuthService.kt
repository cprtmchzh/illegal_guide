package com.department.guide.domain.auth;

import com.department.guide.domain.member.MemberRepository
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
class AuthService (private val memberRepository: MemberRepository) {


}
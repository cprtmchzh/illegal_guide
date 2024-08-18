package com.department.guide.domain.member

import com.department.guide.global.exception.InvalidInputException
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun searchMemberInfo(id: Long): MemberResponse {

        val member = memberRepository.findByIdOrNull(id)
            ?: throw InvalidInputException("id", "해당 유저는 존재하지 않습니다.")

        return member.memberRes()
    }
}
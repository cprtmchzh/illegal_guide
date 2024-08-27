package com.department.guide.domain.member

import com.department.guide.domain.auth.mail.Mail
import com.department.guide.domain.auth.mail.MailRepository
import com.department.guide.domain.auth.mail.MailService
import com.department.guide.global.exception.InvalidInputException
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.StringBuilder
import java.security.SecureRandom
import java.util.Random

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val mailRepository: MailRepository,
    private val mailService: MailService
) {

    fun searchMemberInfo(id: Long): MemberResponse {

        val member: Member = memberRepository.findByIdOrNull(id)
            ?: throw InvalidInputException("id", "해당 유저는 존재하지 않습니다.")

        return member.memberRes()
    }

    fun sendCodeToEmail(toEmail: String) {

        var member: Member? = memberRepository.findByEmail(toEmail)

        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 메일입니다.")
        }

        val title = "이메일 인증 번호"
        val authCode: String = this.createCode()
        val mail = Mail(
            toEmail,
            authCode
        )

        mailService.sendEmail(toEmail, title, authCode)
        mailRepository.save(mail)
    }

    fun createCode(): String {

        val length = 6
        val i: Int
        try {
            val random: Random = SecureRandom.getInstanceStrong()
            val builder = StringBuilder()
            for (i in 1..length) {
                builder.append(random.nextInt(10))
            }
            return builder.toString()
        } catch (e: Exception) {
            throw Exception("코드 생성에 실패하였습니다.")
        }
    }

    fun verifiedCode(email: String, authCode: String): Boolean {

        val redisAuthCode = mailRepository.findByEmail(email).authCode

        if (redisAuthCode.equals(authCode)) {
            return true
        }
        else return false
    }
}
package com.department.guide.domain.mail

import jakarta.transaction.Transactional
import org.springframework.mail.MailSendException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
@Transactional
class MailService(
    private val emailSender: JavaMailSender
) {

    fun sendEmail(toEmail: String, title: String, text: String) {

        val emailForm: SimpleMailMessage = createEmailForm(toEmail, title, text)

        try{
            emailSender.send(emailForm)
        } catch (e: Exception) {
            throw MailSendException("이메일 전송에 실패하였습니다.")
        }
    }

    fun createEmailForm(toEmail: String, title: String, text: String): SimpleMailMessage {

        val message = SimpleMailMessage()
        message.setTo(toEmail)
        message.subject = title
        message.text = text
        return message
    }
}
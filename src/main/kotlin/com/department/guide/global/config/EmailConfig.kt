package com.department.guide.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.Properties

@Configuration
class EmailConfig {

    @Value("\${spring.mail.host}")
    lateinit var host: String

    @Value("\${spring.mail.port}")
    var port: Int? = null

    @Value("\${spring.mail.username}")
    lateinit var username: String

    @Value("\${spring.mail.password}")
    lateinit var password: String

    @Value("\${spring.mail.properties.mail.smtp.auth}")
    var auth: Boolean? = null

    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}")
    var starttlsEnable: Boolean? = null

    @Value("\${spring.mail.properties.mail.smtp.starttls.required}")
    var starttlsRequired: Boolean? = null

    @Value("\${spring.mail.properties.mail.smtp.timeout}")
    var connectionTimeout: Int? = null

    @Value("\${spring.mail.properties.mail.smtp.timeout}")
    var timeout: Int? = null

    @Value("\${spring.mail.properties.mail.smtp.timeout}")
    var writeTimeout: Int? = null

    @Bean
    fun mailSender(): JavaMailSender {

        val mailSender = JavaMailSenderImpl()
        mailSender.host = host
        mailSender.port = port!!
        mailSender.username = username
        mailSender.password = password
        mailSender.defaultEncoding = "UTF-8"
        mailSender.javaMailProperties = getMailProperties()

        return mailSender
    }

    fun getMailProperties(): Properties {

        val properties = Properties()
        properties.put("mail.smtp.auth", auth)
        properties.put("mail.smtp.starttls.enable", starttlsEnable)
        properties.put("mail.smtp.starttls.required", starttlsRequired)
        properties.put("mail.smtp.timeout", connectionTimeout)
        properties.put("mail.smtp.timeout", timeout)
        properties.put("mail.smtp.timeout", writeTimeout)

        return properties
    }
}
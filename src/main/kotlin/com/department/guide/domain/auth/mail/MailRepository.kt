package com.department.guide.domain.auth.mail

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MailRepository : JpaRepository<Mail, String> {

    fun findByEmail(email: String) : Mail
}
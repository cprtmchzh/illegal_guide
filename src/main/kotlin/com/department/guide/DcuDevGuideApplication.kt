package com.department.guide

import com.department.guide.domain.auth.mail.MailRepository
import com.department.guide.global.jwt.RefreshTokenRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["com.department.guide"],
	excludeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
		classes = [RefreshTokenRepository::class, MailRepository::class])])
class DcuDevGuideApplication

fun main(args: Array<String>) {
	runApplication<DcuDevGuideApplication>(*args)
}

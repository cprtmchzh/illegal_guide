package com.department.guide.global.config

import com.department.guide.domain.mail.MailRepository
import com.department.guide.global.jwt.RefreshTokenRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableRedisRepositories(basePackages = ["com.department.guide.global.jwt", "com.department.guide.domain.mail"],
    includeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = [RefreshTokenRepository::class, MailRepository::class])])
class RedisConfig {

     @Value("\${spring.data.redis.host}")
     lateinit var redisHost: String

     @Bean
     fun redisConnectionFactory(): RedisConnectionFactory {

         return LettuceConnectionFactory(RedisStandaloneConfiguration(redisHost, 6379))
     }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {

        return RedisTemplate<Any, Any>().apply {
            this.connectionFactory = redisConnectionFactory()

            this.keySerializer = StringRedisSerializer()
            this.hashKeySerializer = StringRedisSerializer()
            this.valueSerializer = StringRedisSerializer()
        }
    }
}
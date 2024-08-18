package com.department.guide.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(Components())
        .info(apiInfo())

    private fun apiInfo() = Info()
        .title("불법 길라 잡이")
        .description("우리 가게 정상영업 합니다.")
        .version("1.0.0")
}
package com.department.guide.global.config

import com.department.guide.domain.info.BenefitRepository
import com.department.guide.domain.info.RequirementsRepository
import com.department.guide.domain.mail.MailRepository
import com.department.guide.global.jwt.RefreshTokenRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.convert.DbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.department.guide.domain.info"],
    includeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = [BenefitRepository::class, RequirementsRepository::class])])
class MongoDBConfig (
    private val mongoMappingContext: MongoMappingContext
) {

    @Bean
    fun mappingMongoConverter(mongoDatabaseFactory: MongoDatabaseFactory,
                              mongoMappingContext: MongoMappingContext): MappingMongoConverter {

        val dbRefResolver: DbRefResolver = DefaultDbRefResolver(mongoDatabaseFactory)
        val converter = MappingMongoConverter(dbRefResolver, mongoMappingContext)
        converter.setTypeMapper(DefaultMongoTypeMapper(null))
        return converter
    }
}
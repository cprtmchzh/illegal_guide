package com.department.guide.domain.info

import org.springframework.data.mongodb.repository.MongoRepository

interface BenefitRepository : MongoRepository<Benefit, String> {
}
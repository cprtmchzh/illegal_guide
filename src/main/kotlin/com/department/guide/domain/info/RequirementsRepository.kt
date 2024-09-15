package com.department.guide.domain.info

import org.springframework.data.mongodb.repository.MongoRepository

interface RequirementsRepository : MongoRepository<Requirements, String> {
}
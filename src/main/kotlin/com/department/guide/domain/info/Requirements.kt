package com.department.guide.domain.info

import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("requirements")
data class Requirements(

    @Id
    var id: String? = null,

    var duration: String,

    @Field("list")
    val list: org.bson.Document
)
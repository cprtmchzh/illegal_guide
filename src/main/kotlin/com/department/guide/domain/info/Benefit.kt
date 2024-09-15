package com.department.guide.domain.info

import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("benefit")
class Benefit (

    @Id
    var id: String? = null,

    var enterprise: String,

    @Field("list")
    val list: String
)
package com.department.guide.domain.member

import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "member_email", updatable = false)
    val email: String,

    @Column(name = "member_password")
    val password: String,

    @Column(name = "nickname")
    val nickname: String,
)
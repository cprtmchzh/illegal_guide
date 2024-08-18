package com.department.guide.domain.member

import jakarta.persistence.*

enum class ROLE {
    ADMIN,
    MEMBER
}

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

    @Column(name = "member_nickname")
    val nickname: String,

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    val role: ROLE
) {

    fun memberRes(): MemberResponse =
        MemberResponse(
            id!!,
            email,
            nickname
        )
}
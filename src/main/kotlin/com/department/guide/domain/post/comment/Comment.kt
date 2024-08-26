package com.department.guide.domain.post.comment

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
class Comment (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "comment_post")
    val postId: Long,

    @Column(name = "comment_user")
    val user: String,

    @Column(name = "comment_content")
    val content: String,

    @Column(name = "comment_timestamp")
    val timestamp: LocalDateTime
)
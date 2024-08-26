package com.department.guide.domain.post

import com.department.guide.domain.member.MemberResponse
import com.department.guide.domain.post.comment.Comment
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "post")
class Post (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "post_title")
    val title: String,

    @Column(name = "post_user")
    val user: String,

    @Column(name = "post_content")
    val content: String,

    @Column(name = "post_hits")
    val hits: Long,

    @Column(name = "post_timestamp")
    val timestamp: LocalDateTime,

    @OneToMany(mappedBy = "postId", fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    var comment: MutableList<Comment> = ArrayList()
) {

    fun postRes(): PostResponse =
        PostResponse(
            id!!,
            title,
            hits,
            timestamp
        )
}
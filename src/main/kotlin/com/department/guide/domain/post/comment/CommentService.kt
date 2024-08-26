package com.department.guide.domain.post.comment

import com.department.guide.domain.member.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Transactional
class CommentService(
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository
) {

    fun createComment(commentRequest: CommentRequest,
                      postId: Long, userId: Long): Comment {

        val comment = Comment(
            null,
            postId,
            memberRepository.findById(userId).get().nickname,
            commentRequest.content,
            LocalDateTime.now()
        )

        return commentRepository.save(comment)
    }

    fun deleteComment(commentId: Long) {

        return commentRepository.deleteById(commentId)
    }
}
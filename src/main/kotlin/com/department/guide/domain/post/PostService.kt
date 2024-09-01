package com.department.guide.domain.post

import com.department.guide.domain.member.MemberRepository
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.Optional

@Service
@Transactional
class PostService(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository
) {

    fun getPostList(page: Int, keyword: String): Page<PostResponse>? {

        val pageable: Pageable = PageRequest.of(page - 1, 10)
        val spec: Specification<Post> = searchPost(keyword)
        return postRepository.findAllDesc(spec, pageable)
            ?.map { it.postRes() }
    }

    fun getPost(id: Long): Optional<Post> {

        val post = this.postRepository.findById(id)

        if (post.isPresent) {
            val postInfo = post.get()
            postInfo.hits++
            this.postRepository.save(postInfo)
        }

        return post

    }

    fun searchPost(keyword: String): Specification<Post> {

        return Specification { q: Root<Post>, query: CriteriaQuery<*>, cb: CriteriaBuilder ->
            query.distinct(true)

            cb.or(
                cb.like(q.get("title"), "%" + keyword + "%"),
                cb.like(q.get("content"), "%" + keyword + "%"))

        }
    }

    fun createPost(postRequest: PostRequest, userId: Long): Post {

        val post = Post(

            null,
            postRequest.title,
            memberRepository.findById(userId).get().nickname,
            postRequest.content,
            0,
            LocalDateTime.now()
        )

        return postRepository.save(post)
    }

    fun deletePost(postId: Long) {

        return postRepository.deleteById(postId)
    }
}
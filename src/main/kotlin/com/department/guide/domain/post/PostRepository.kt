package com.department.guide.domain.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository : JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p ORDER BY p.timestamp DESC")
    fun findAllDesc(pageable: Pageable) : Page<Post>?

    @Query("SELECT p FROM Post p ORDER BY p.timestamp DESC")
    fun findAllDesc(spec: Specification<Post>, pageable: Pageable): Page<Post>?
}
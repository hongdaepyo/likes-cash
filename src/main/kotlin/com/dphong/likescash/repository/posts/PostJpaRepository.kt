package com.dphong.likescash.repository.posts

import com.dphong.likescash.domain.posts.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository: JpaRepository<Post, Long> {

    fun findAllByProductId(productId: Long): List<Post>
}

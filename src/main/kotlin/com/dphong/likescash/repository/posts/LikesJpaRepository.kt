package com.dphong.likescash.repository.posts

import com.dphong.likescash.domain.posts.Likes
import org.springframework.data.jpa.repository.JpaRepository

interface LikesJpaRepository : JpaRepository<Likes, Long> {
    fun findByMemberIdAndPostId(memberId: Long, postId: Long): Likes?
}

package com.dphong.likescash.repository.posts

import com.dphong.likescash.domain.posts.Likes

interface LikesRepository {
    fun save(likes: Likes): Likes
    fun findByMemberIdAndPostId(memberId: Long, postId: Long): Likes?
    fun delete(likes: Likes)
}

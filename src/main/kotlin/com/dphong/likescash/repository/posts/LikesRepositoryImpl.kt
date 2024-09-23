package com.dphong.likescash.repository.posts

import com.dphong.likescash.domain.posts.Likes
import org.springframework.stereotype.Repository

@Repository
class LikesRepositoryImpl(
    private val likesJpaRepository: LikesJpaRepository
) : LikesRepository {

    override fun save(likes: Likes): Likes {
        return likesJpaRepository.save(likes)
    }

    override fun findByMemberIdAndPostId(memberId: Long, postId: Long): Likes? {
        return likesJpaRepository.findByMemberIdAndPostId(memberId, postId)
    }
}

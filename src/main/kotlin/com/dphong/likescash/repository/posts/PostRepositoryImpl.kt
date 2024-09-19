package com.dphong.likescash.repository.posts

import com.dphong.likescash.domain.posts.Post
import org.springframework.stereotype.Repository

@Repository
class PostRepositoryImpl(private val postJpaRepository: PostJpaRepository) : PostRepository {

    override fun save(post: Post): Post {
        return postJpaRepository.save(post)
    }

    override fun findAllByProductId(productId: Long): List<Post> {
        return postJpaRepository.findAllByProductId(productId)
    }
}

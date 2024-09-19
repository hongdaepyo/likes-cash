package com.dphong.likescash.repository.posts

import com.dphong.likescash.domain.posts.Post

interface PostRepository {

    fun save(post: Post): Post
    fun findAllByProductId(productId: Long): List<Post>
}

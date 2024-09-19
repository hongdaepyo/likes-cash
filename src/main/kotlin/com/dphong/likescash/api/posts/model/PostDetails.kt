package com.dphong.likescash.api.posts.model

import com.dphong.likescash.domain.posts.Post
import com.dphong.likescash.domain.posts.PostReaction

data class PostDetails(
    val id: Long,
    val linkUrl: String,
    val reaction: PostReaction
) {
    companion object {
        fun from(post: Post) = PostDetails(post.id!!, post.linkUrl, post.reaction)
    }
}

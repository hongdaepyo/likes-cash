package com.dphong.likescash.api.posts.model

import com.dphong.likescash.domain.posts.PostPlatform

data class RegisterPostRequest(
    val productId: Long,
    val linkUrl: String,
    val platform: PostPlatform
)

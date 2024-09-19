package com.dphong.likescash.api.posts.model

import com.dphong.likescash.domain.posts.PostPlatform
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class SubmitPostRequest(
    @NotNull(message = "상품 ID가 비어 있습니다") val productId: Long,
    @NotBlank(message = "링크가 비어 있습니다") val linkUrl: String,
    val platform: PostPlatform = PostPlatform.UNKNOWN
)

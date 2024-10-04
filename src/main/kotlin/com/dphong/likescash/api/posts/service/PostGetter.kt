package com.dphong.likescash.api.posts.service

import com.dphong.likescash.api.posts.model.PostDetails
import com.dphong.likescash.common.annotation.ReadOnlyService
import com.dphong.likescash.common.response.DataResult
import com.dphong.likescash.repository.posts.PostRepository

@ReadOnlyService
class PostGetter(
    private val postRepository: PostRepository
) {

    fun getPostsByProductId(productId: Long): DataResult<List<PostDetails>> = DataResult(
        postRepository.findAllByProductId(productId)
            .sortedByDescending { it.submittedAt }
            .map(PostDetails.Companion::from)
    )
}

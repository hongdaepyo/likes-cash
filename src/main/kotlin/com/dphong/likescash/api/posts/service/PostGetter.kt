package com.dphong.likescash.api.posts.service

import com.dphong.likescash.api.posts.model.PostDetails
import com.dphong.likescash.common.annotation.ReadOnlyService

@ReadOnlyService
class PostGetter(

) {

    fun getPostsByProductId(productId: Long): List<PostDetails> {
        return emptyList()
    }
}

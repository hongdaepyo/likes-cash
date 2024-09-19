package com.dphong.likescash.api.posts.controller

import com.dphong.likescash.api.posts.model.PostDetails
import com.dphong.likescash.api.posts.service.PostGetter
import com.dphong.likescash.common.reponse.DataResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/posts")
@RestController
class SearchPostController(
    private val postGetter: PostGetter
) {

    @GetMapping
    fun getProductPosts(
        @RequestParam("productId") productId: Long
    ): ResponseEntity<DataResult<List<PostDetails>>> {
        return postGetter.getPostsByProductId(productId).toResponseEntity()
    }
}

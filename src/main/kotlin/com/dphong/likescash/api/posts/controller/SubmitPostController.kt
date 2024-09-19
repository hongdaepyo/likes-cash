package com.dphong.likescash.api.posts.controller

import com.dphong.likescash.api.posts.model.SubmitPostRequest
import com.dphong.likescash.api.posts.model.SubmitPostResponse
import com.dphong.likescash.api.posts.model.SubmitPostStatus
import com.dphong.likescash.api.posts.service.SubmitPostService
import com.dphong.likescash.common.annotation.LoginMember
import com.dphong.likescash.common.config.auth.MemberFacade
import com.dphong.likescash.common.reponse.StatusDataResult
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/posts")
@RestController
class SubmitPostController(
    private val submitPostService: SubmitPostService
) {
    @PostMapping
    fun submitPost(
        @Valid @RequestBody request: SubmitPostRequest,
        @LoginMember member: MemberFacade
    ): ResponseEntity<StatusDataResult<SubmitPostStatus, SubmitPostResponse>> =
        submitPostService.submit(member.memberId, request).toResponseEntity()
}

package com.dphong.likescash.api.posts.controller

import com.dphong.likescash.api.posts.model.ReactionPostResponse
import com.dphong.likescash.api.posts.model.ReactionPostStatus
import com.dphong.likescash.api.posts.service.ReactionPostService
import com.dphong.likescash.common.annotation.LoginMember
import com.dphong.likescash.common.config.auth.MemberFacade
import com.dphong.likescash.common.reponse.StatusDataResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/posts")
@RestController
class ReactionPostController(
    private val reactionPostService: ReactionPostService
) {

    @PostMapping("/{postId}/likes")
    fun likes(
        @PathVariable("postId") postId: Long,
        @LoginMember member: MemberFacade
    ): ResponseEntity<StatusDataResult<ReactionPostStatus, ReactionPostResponse>> {
        return reactionPostService.likes(member.memberId, postId).toResponseEntity()
    }

    @DeleteMapping("/{postId}/likes")
    fun cancelLikes(
        @PathVariable("postId") postId: Long,
        @LoginMember member: MemberFacade
    ): ResponseEntity<StatusDataResult<ReactionPostStatus, ReactionPostResponse>> {
        return reactionPostService.cancelLikes(member.memberId, postId).toResponseEntity()
    }
}

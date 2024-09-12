package com.dphong.likescash.api.posts.controller

import com.dphong.likescash.api.posts.model.RegisterPostRequest
import com.dphong.likescash.common.annotation.LoginMember
import com.dphong.likescash.common.config.auth.MemberFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/posts")
@RestController
class RegisterPostController {

    @PostMapping
    fun registerPost(
        @RequestBody request: RegisterPostRequest,
        @LoginMember member: MemberFacade
    ): ResponseEntity<Nothing> {

        return ResponseEntity.ok(null)
    }
}

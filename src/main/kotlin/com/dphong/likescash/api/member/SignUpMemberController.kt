package com.dphong.likescash.api.member

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/member")
@RestController
class SignUpMemberController {

    @PostMapping("/sign-up")
    fun signUp(): ResponseEntity<*> {
        return ResponseEntity.ok(null)
    }
}

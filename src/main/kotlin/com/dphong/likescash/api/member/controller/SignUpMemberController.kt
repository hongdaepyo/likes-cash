package com.dphong.likescash.api.member.controller

import com.dphong.likescash.common.reponse.ApiResponse
import com.dphong.likescash.common.reponse.CommonStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/member")
@RestController
class SignUpMemberController {

    @PostMapping("/sign-up")
    fun signUp(): ResponseEntity<ApiResponse<CommonStatus, Unit>> {
        return ResponseEntity.ok(ApiResponse(CommonStatus.SUCCESS, null))
    }
}

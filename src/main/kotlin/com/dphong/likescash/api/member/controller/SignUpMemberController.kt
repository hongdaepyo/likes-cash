package com.dphong.likescash.api.member.controller

import com.dphong.likescash.api.member.model.SignUpMemberRequest
import com.dphong.likescash.api.member.model.SignUpMemberResponse
import com.dphong.likescash.api.member.model.SignUpMemberStatus
import com.dphong.likescash.api.member.service.SignUpMemberService
import com.dphong.likescash.common.reponse.StatusDataResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/member")
@RestController
class SignUpMemberController(private val signUpMemberService: SignUpMemberService) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpMemberRequest): ResponseEntity<StatusDataResult<SignUpMemberStatus, SignUpMemberResponse>> {
        return signUpMemberService.signUp(request).toResponseEntity()
    }
}

package com.dphong.likescash.api.member.controller

import com.dphong.likescash.common.reponse.ApiResponse
import com.dphong.likescash.common.reponse.CommonStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/member")
@RestController
class SignInMemberController(
    private val authenticationManager: AuthenticationManager,
) {

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<ApiResponse<CommonStatus, Unit>> {
        val authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.id, request.password)
        authenticationManager.authenticate(authenticationRequest)

        return ResponseEntity.ok(ApiResponse(CommonStatus.SUCCESS, null))
    }

    data class SignInRequest(val id: String, val password: String)
}

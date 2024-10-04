package com.dphong.likescash.api.product.controller

import com.dphong.likescash.api.product.model.RegisterProductRequest
import com.dphong.likescash.api.product.model.RegisterProductResponse
import com.dphong.likescash.api.product.model.RegisterProductStatus
import com.dphong.likescash.api.product.service.RegisterProductService
import com.dphong.likescash.common.annotation.LoginMember
import com.dphong.likescash.common.config.auth.MemberFacade
import com.dphong.likescash.common.response.StatusDataResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/sellers")
@RestController
class RegisterProductController(private val registerProductService: RegisterProductService) {

    @PostMapping("/product")
    fun register(
        @RequestBody request: RegisterProductRequest,
        @LoginMember member: MemberFacade
    ): ResponseEntity<StatusDataResult<RegisterProductStatus, RegisterProductResponse>> =
        registerProductService.register(member.memberId, request).toResponseEntity()
}

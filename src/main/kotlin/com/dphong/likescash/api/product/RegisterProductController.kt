package com.dphong.likescash.api.product

import com.dphong.likescash.api.product.model.RegisterProductRequest
import com.dphong.likescash.api.product.model.RegisterProductResponse
import com.dphong.likescash.api.product.model.RegisterProductStatus
import com.dphong.likescash.common.reponse.StatusDataResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/sellers")
@RestController
class RegisterProductController(private val registerProductService: RegisterProductService) {

    @PostMapping("/product")
    fun register(@RequestBody request: RegisterProductRequest): ResponseEntity<StatusDataResult<RegisterProductStatus, RegisterProductResponse>> {
        // TODO: 회원 아이디 얻어오는 부분 수정 필요
        return registerProductService.register(1L, request).toResponseEntity()
    }
}

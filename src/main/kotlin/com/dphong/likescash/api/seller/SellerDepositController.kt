package com.dphong.likescash.api.seller

import com.dphong.likescash.api.seller.model.SellerDepositOrderRequest
import com.dphong.likescash.api.seller.model.SellerDepositOrderResponse
import com.dphong.likescash.api.seller.model.SellerDepositRequest
import com.dphong.likescash.api.seller.model.SellerDepositResponse
import com.dphong.likescash.common.annotation.LoginMember
import com.dphong.likescash.common.config.auth.MemberFacade
import com.dphong.likescash.common.response.CommonStatus
import com.dphong.likescash.common.response.StatusDataResult
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/sellers/deposit")
@RestController
class SellerDepositController(
    private val sellerDepositService: SellerDepositService,
    private val sellerDepositOrderGetter: SellerDepositOrderGetter
) {

    @GetMapping
    fun getDepositOrders(
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "10") size: Int,
        @LoginMember member: MemberFacade
    ): ResponseEntity<*> =
        sellerDepositOrderGetter.getOrders(member.memberId, PageRequest.of(page, size)).toResponseEntity()

    @PostMapping("/order")
    fun orderDeposit(
        @Valid @RequestBody request: SellerDepositOrderRequest,
        @LoginMember member: MemberFacade
    ): ResponseEntity<StatusDataResult<CommonStatus, SellerDepositOrderResponse>> =
        sellerDepositService.order(member.memberId, request).toResponseEntity()

    @PutMapping
    fun chargeDeposit(
        @RequestBody request: SellerDepositRequest,
        @LoginMember member: MemberFacade
    ): ResponseEntity<StatusDataResult<CommonStatus, SellerDepositResponse>> =
        sellerDepositService.charge(member.memberId, request).toResponseEntity()
}

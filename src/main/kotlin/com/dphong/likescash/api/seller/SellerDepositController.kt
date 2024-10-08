package com.dphong.likescash.api.seller

import com.dphong.likescash.api.seller.model.SellerDepositRequest
import com.dphong.likescash.api.seller.model.SellerDepositResponse
import com.dphong.likescash.common.annotation.LoginMember
import com.dphong.likescash.common.config.auth.MemberFacade
import com.dphong.likescash.common.response.CommonStatus
import com.dphong.likescash.common.response.StatusDataResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/sellers/deposit")
@RestController
class SellerDepositController(
    private val sellerDepositService: SellerDepositService
) {

    @PutMapping
    fun chargeDeposit(
        @RequestBody request: SellerDepositRequest,
        @LoginMember member: MemberFacade
    ): ResponseEntity<StatusDataResult<CommonStatus, SellerDepositResponse>> =
        sellerDepositService.charge(member.memberId, request).toResponseEntity()
}

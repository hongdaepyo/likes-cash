package com.dphong.likescash.api.seller

import com.dphong.likescash.api.seller.model.SellerDepositRequest
import com.dphong.likescash.api.seller.model.SellerDepositResponse
import com.dphong.likescash.common.exception.ResourceNotFoundException
import com.dphong.likescash.common.response.CommonStatus
import com.dphong.likescash.common.response.StatusDataResult
import com.dphong.likescash.domain.member.Seller
import com.dphong.likescash.domain.seller.SellerDepositOrderStatus
import com.dphong.likescash.repository.SellerDepositOrderRepository
import com.dphong.likescash.repository.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SellerDepositService(
    private val memberRepository: MemberRepository,
    private val sellerDepositOrderRepository: SellerDepositOrderRepository
) {
    @Transactional
    fun charge(sellerId: Long, request: SellerDepositRequest): StatusDataResult<CommonStatus, SellerDepositResponse> {
        val seller = memberRepository.findByIdOrNull(sellerId) as? Seller
            ?: throw ResourceNotFoundException("Member", sellerId)
        val order = sellerDepositOrderRepository.findByOrderNumber(request.orderNumber)
            ?.takeIf { it.seller.id == seller.id }
            ?.takeIf { it.status == SellerDepositOrderStatus.ORDERED }
            ?: throw ResourceNotFoundException("Could not found seller(${seller.id})'s order: ${request.orderNumber}")

        seller.charge(order.amount)
        return StatusDataResult(CommonStatus.SUCCESS, SellerDepositResponse(seller.id!!, seller.deposit.amount))
    }
}

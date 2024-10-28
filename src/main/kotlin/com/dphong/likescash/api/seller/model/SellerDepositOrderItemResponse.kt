package com.dphong.likescash.api.seller.model

import com.dphong.likescash.domain.seller.SellerDepositOrder
import com.dphong.likescash.domain.seller.SellerDepositOrderStatus
import java.time.Instant

data class SellerDepositOrderItemResponse(
    val id: Long,
    val status: SellerDepositOrderStatus,
    val amount: Long,
    val orderNumber: String,
    val orderedAt: Instant
) {
    companion object {
        fun of(order: SellerDepositOrder): SellerDepositOrderItemResponse {
            return SellerDepositOrderItemResponse(
                id = order.id!!,
                status = order.status,
                amount = order.amount,
                orderNumber = order.orderNumber,
                orderedAt = order.orderedAt
            )
        }
    }
}

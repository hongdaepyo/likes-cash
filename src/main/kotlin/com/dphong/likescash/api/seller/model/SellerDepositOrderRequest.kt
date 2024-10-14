package com.dphong.likescash.api.seller.model

data class SellerDepositOrderRequest(
    val amount: Long,
    val paymentType: PaymentType,
    val transactionId: String
)

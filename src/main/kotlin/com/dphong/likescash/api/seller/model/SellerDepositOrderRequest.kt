package com.dphong.likescash.api.seller.model

import com.dphong.likescash.common.Config.MAX_DEPOSIT_AMOUNT
import com.dphong.likescash.common.Config.MIN_DEPOSIT_AMOUNT
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class SellerDepositOrderRequest(
    @Min(value = MIN_DEPOSIT_AMOUNT, message = "최소 충전 예치금은 ${MIN_DEPOSIT_AMOUNT}원 이상입니다.")
    @Max(value = MAX_DEPOSIT_AMOUNT, message = "최대 충전 예치금은 ${MAX_DEPOSIT_AMOUNT}원 이하입니다.")
    val amount: Long,
    val paymentType: PaymentType,
    val transactionId: String
)

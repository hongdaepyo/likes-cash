package com.dphong.likescash.fixtures

import com.dphong.likescash.domain.member.Seller
import com.dphong.likescash.domain.seller.SellerDepositOrder

object SellerDepositOrderFixture {

    fun create(
        seller: Seller = MemberFixture.seller(),
        amount: Long = 100,
        orderNumber: String = "testOrderNumber"
    ) = SellerDepositOrder.of(
        seller = seller,
        amount = amount,
        orderNumber = orderNumber
    )
}

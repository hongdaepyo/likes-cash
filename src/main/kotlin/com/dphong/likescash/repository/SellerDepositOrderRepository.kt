package com.dphong.likescash.repository

import com.dphong.likescash.domain.seller.SellerDepositOrder

interface SellerDepositOrderRepository {
    fun findByOrderNumber(orderNumber: String): SellerDepositOrder?
    fun save(sellerDepositOrder: SellerDepositOrder): SellerDepositOrder
}

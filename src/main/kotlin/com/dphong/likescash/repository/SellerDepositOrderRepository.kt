package com.dphong.likescash.repository

import com.dphong.likescash.domain.seller.SellerDepositOrder
import org.springframework.data.domain.Pageable

interface SellerDepositOrderRepository {
    fun findByOrderNumber(orderNumber: String): SellerDepositOrder?
    fun save(sellerDepositOrder: SellerDepositOrder): SellerDepositOrder
    fun findAllBySellerId(sellerId: Long, pagination: Pageable): List<SellerDepositOrder>
}

package com.dphong.likescash.repository

import com.dphong.likescash.domain.seller.SellerDepositOrder
import org.springframework.data.jpa.repository.JpaRepository

interface SellerDepositOrderJpaRepository : JpaRepository<SellerDepositOrder, Long> {

    fun findByOrderNumber(orderNumber: String): SellerDepositOrder?
}

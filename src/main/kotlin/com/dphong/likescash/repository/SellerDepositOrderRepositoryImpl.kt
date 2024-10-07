package com.dphong.likescash.repository

import com.dphong.likescash.domain.seller.SellerDepositOrder
import org.springframework.stereotype.Repository

@Repository
class SellerDepositOrderRepositoryImpl(
    private val sellerDepositOrderJpaRepository: SellerDepositOrderJpaRepository
) : SellerDepositOrderRepository {

    override fun findByOrderNumber(orderNumber: String): SellerDepositOrder? =
        sellerDepositOrderJpaRepository.findByOrderNumber(orderNumber)

    override fun save(sellerDepositOrder: SellerDepositOrder): SellerDepositOrder =
        sellerDepositOrderJpaRepository.save(sellerDepositOrder)
}

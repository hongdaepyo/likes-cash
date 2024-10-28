package com.dphong.likescash.api.seller

import com.dphong.likescash.api.seller.model.SellerDepositOrderItemResponse
import com.dphong.likescash.common.annotation.ReadOnlyService
import com.dphong.likescash.common.response.DataResult
import com.dphong.likescash.repository.SellerDepositOrderRepository
import org.springframework.data.domain.Pageable

@ReadOnlyService
class SellerDepositOrderGetter(
    private val repository: SellerDepositOrderRepository
) {

    fun getOrders(sellerId: Long, pagination: Pageable): DataResult<List<SellerDepositOrderItemResponse>> {
        val orders = repository.findAllBySellerId(sellerId, pagination)
        return DataResult(orders.map(SellerDepositOrderItemResponse.Companion::of))
    }
}

package com.dphong.likescash.api.product.model

import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.product.Product
import com.dphong.likescash.domain.product.ProductSchedule
import java.time.Instant

data class RegisterProductRequest(
    val name: String,
    val startedAt: Instant? = null,
    val endedAt: Instant? = null
) {

    fun toProduct(seller: Member): Product {
        return Product.of(name, seller, ProductSchedule(startedAt, endedAt))
    }
}

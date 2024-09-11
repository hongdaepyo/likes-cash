package com.dphong.likescash.api.product.model

import com.dphong.likescash.domain.Member
import com.dphong.likescash.domain.Product

data class RegisterProductRequest(val name: String) {

    fun toProduct(seller: Member): Product {
        return Product.of(name, seller)
    }
}

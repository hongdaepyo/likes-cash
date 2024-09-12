package com.dphong.likescash.api.product.model

import com.dphong.likescash.domain.product.Product

data class ProductDetails(
    val id: Long,
    val name: String,
    val seller: String,
    val missions: List<ProductMissionDetails>
) {
    companion object {
        fun from(product: Product) = ProductDetails(
            id = product.id!!,
            name = product.name,
            seller = product.seller.name.orEmpty(),
            missions = product.missions.map { ProductMissionDetails(it.id!!, it.topic.orEmpty()) }
        )
    }
}

data class ProductMissionDetails(
    val id: Long,
    val topic: String
)

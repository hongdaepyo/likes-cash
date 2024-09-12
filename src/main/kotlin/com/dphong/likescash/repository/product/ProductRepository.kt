package com.dphong.likescash.repository.product

import com.dphong.likescash.domain.product.Product

interface ProductRepository {

    fun save(product: Product): Product
    fun findAll(): List<Product>
    fun findByIdOrNull(productId: Long): Product?
}

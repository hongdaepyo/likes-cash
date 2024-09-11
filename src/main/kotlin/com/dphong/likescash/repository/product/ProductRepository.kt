package com.dphong.likescash.repository.product

import com.dphong.likescash.domain.Product

interface ProductRepository {

    fun save(product: Product): Product
}

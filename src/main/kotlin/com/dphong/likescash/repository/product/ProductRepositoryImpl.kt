package com.dphong.likescash.repository.product

import com.dphong.likescash.domain.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val productJpaRepository: ProductJpaRepository
) : ProductRepository {

    override fun save(product: Product): Product {
        return productJpaRepository.save(product)
    }

    override fun findAll(): List<Product> =
        productJpaRepository.findAll().filter { it.isVisible }

    override fun findByIdOrNull(productId: Long): Product? {
        return productJpaRepository.findByIdOrNull(productId)
    }
}

package com.dphong.likescash.api.product.service

import com.dphong.likescash.api.product.model.ProductDetails
import com.dphong.likescash.common.exception.ResourceNotFoundException
import com.dphong.likescash.common.response.DataResult
import com.dphong.likescash.repository.product.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ProductGetter(
    private val productRepository: ProductRepository
) {

    fun getProducts(): DataResult<List<ProductDetails>> {
        return DataResult(productRepository.findAll().map { ProductDetails.from(it) })
    }

    fun getProductDetails(productId: Long): DataResult<ProductDetails> {
        val product = productRepository.findByIdOrNull(productId)
            ?.takeIf { it.isVisible }
            ?: throw ResourceNotFoundException("Product", productId)

        return DataResult(ProductDetails.from(product))
    }
}

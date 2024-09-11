package com.dphong.likescash.api.product

import com.dphong.likescash.common.reponse.DataResult
import com.dphong.likescash.domain.Product
import com.dphong.likescash.repository.product.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ProductGetter(
    private val productRepository: ProductRepository
) {

    fun getProducts(): DataResult<List<Product>> {
        return DataResult(productRepository.findAll())
    }
}

package com.dphong.likescash.api.product

import com.dphong.likescash.domain.Product
import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.mock.FakeProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductGetterTest {

    private val productRepository: FakeProductRepository = FakeProductRepository()
    private val productGetter = ProductGetter(productRepository)

    @Test
    fun `상품을 조회한다`() {
        // given
        saveVisibleProduct()
        saveInvisibleProduct()

        // when
        val products = productGetter.getProducts()

        // then
        assertThat(products.data).isNotEmpty
        assertThat(products.data).hasSize(1)
    }

    private fun saveInvisibleProduct() {
        productRepository.save(
            Product.of("testProduct2", MemberFixture.seller())
        )
    }

    private fun saveVisibleProduct() {
        productRepository.save(
            Product.of("testProduct1", MemberFixture.seller()).apply {
                approval()
            }
        )
    }
}

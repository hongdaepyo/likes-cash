package com.dphong.likescash.api.product

import com.dphong.likescash.domain.Product
import com.dphong.likescash.domain.ProductMission
import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.mock.FakeProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductGetterTest {

    private val productRepository: FakeProductRepository = FakeProductRepository()
    private val productGetter = ProductGetter(productRepository)

    @Test
    fun `상품을 목록을 조회한다`() {
        // given
        saveVisibleProduct()
        saveInvisibleProduct()

        // when
        val products = productGetter.getProducts()

        // then
        assertThat(products.data).isNotEmpty
        assertThat(products.data).hasSize(1)
    }

    @Test
    fun `상품을 상세를 조회한다`() {
        // given
        val product = saveVisibleProduct()
        val mission = ProductMission(1L, product).apply { updateTopic("testTopic") }
        product.addMission(
            mission
        )

        // when
        val result = productGetter.getProductDetails(product.id!!)

        // then
        assertThat(result.data!!.name).isEqualTo(product.name)
        assertThat(result.data!!.missions.first().topic).isEqualTo(mission.topic)
    }

    private fun saveInvisibleProduct(): Product {
        return productRepository.save(
            Product.of("testProduct2", MemberFixture.seller())
        )
    }

    private fun saveVisibleProduct(): Product {
        return productRepository.save(
            Product.of("testProduct1", MemberFixture.seller()).apply {
                approval()
            }
        )
    }
}

package com.dphong.likescash.repository.product

import com.dphong.likescash.RepositoryTest
import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.product.Product
import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.fixtures.ProductFixture
import com.querydsl.jpa.impl.JPAQueryFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductRepositoryImplTest(
    private val productJpaRepository: ProductJpaRepository,
    factory: JPAQueryFactory
) : RepositoryTest() {

    private val repository: ProductRepository = ProductRepositoryImpl(productJpaRepository, factory)

    @Test
    fun `상품을 저장한다`() {
        // given
        val seller = entityManager.persist(MemberFixture.seller())
        val product = Product.of("testProductName", seller)
        // when
        repository.save(product)
        // then
        assertThat(productJpaRepository.count()).isEqualTo(1)
    }

    @Test
    fun `상품 목록을 조회한다`() {
        // given
        val seller = entityManager.persist(MemberFixture.seller())
        repository.save(createVisibleProduct("testProductName1", seller))
        repository.save(createVisibleProduct("testProductName2", seller))
        repository.save(createVisibleProduct("testProductName3", seller))
        // when
        val products = repository.findAll()

        // then
        assertThat(products).hasSize(3)
    }

    @Test
    fun `상품 상세를 조회한다`() {
        // given
        val seller = entityManager.persist(MemberFixture.seller())
        repository.save(createVisibleProduct("testProductName1", seller))

        // when
        val product = repository.findByIdOrNull(1L)

        // then
        assertThat(product).isNotNull
    }

    private fun createVisibleProduct(name: String, seller: Member): Product =
        ProductFixture.createVisible(name = name, seller = seller)
}

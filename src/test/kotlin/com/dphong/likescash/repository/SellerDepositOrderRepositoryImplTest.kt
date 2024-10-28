package com.dphong.likescash.repository

import com.dphong.likescash.RepositoryTest
import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.fixtures.SellerDepositOrderFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest

class SellerDepositOrderRepositoryImplTest(
    private val jpaRepository: SellerDepositOrderJpaRepository
): RepositoryTest() {

    private lateinit var repository: SellerDepositOrderRepository

    @BeforeEach
    fun setUp() {
        repository = SellerDepositOrderRepositoryImpl(jpaRepository)
    }

    @Test
    fun `예치금 주문 목록을 조회한다`() {
        // given
        val seller = entityManager.persist(MemberFixture.seller())
        repository.save(SellerDepositOrderFixture.create(seller = seller, orderNumber = "testOrderNumber1"))
        repository.save(SellerDepositOrderFixture.create(seller = seller, orderNumber = "testOrderNumber2"))
        repository.save(SellerDepositOrderFixture.create(seller = seller, orderNumber = "testOrderNumber3"))
        repository.save(SellerDepositOrderFixture.create(seller = seller, orderNumber = "testOrderNumber4"))
        repository.save(SellerDepositOrderFixture.create(seller = seller, orderNumber = "testOrderNumber5"))

        // when
        val orders = repository.findAllBySellerId(seller.id!!, PageRequest.of(0, 3))

        // then
        assertThat(orders)
            .hasSize(3)
            .extracting("orderNumber")
            .containsExactly("testOrderNumber1", "testOrderNumber2", "testOrderNumber3")
    }
}

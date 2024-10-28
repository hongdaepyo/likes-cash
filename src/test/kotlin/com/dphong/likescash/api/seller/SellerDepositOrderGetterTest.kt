package com.dphong.likescash.api.seller

import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.fixtures.SellerDepositOrderFixture
import com.dphong.likescash.mock.FakeSellerDepositOrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest

class SellerDepositOrderGetterTest {

    @Test
    fun `예치금 충전 주문을 조회한다`() {
        // given
        val repository = FakeSellerDepositOrderRepository()
        val getter = SellerDepositOrderGetter(repository)

        val seller = MemberFixture.seller(1L)
        repository.save(SellerDepositOrderFixture.create(seller, amount = 1000, orderNumber = "testOrderNumber1"))
        repository.save(SellerDepositOrderFixture.create(seller, amount = 1000, orderNumber = "testOrderNumber2"))
        repository.save(SellerDepositOrderFixture.create(seller, amount = 1000, orderNumber = "testOrderNumber3"))
        repository.save(SellerDepositOrderFixture.create(seller, amount = 1000, orderNumber = "testOrderNumber4"))
        repository.save(SellerDepositOrderFixture.create(seller, amount = 1000, orderNumber = "testOrderNumber5"))

        // when
        // then
        val orders = getter.getOrders(seller.id!!, PageRequest.of(0, 3))
        assertThat(orders.data)
            .hasSize(3)
            .extracting("orderNumber")
            .containsExactly("testOrderNumber1", "testOrderNumber2", "testOrderNumber3")
    }
}

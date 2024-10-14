package com.dphong.likescash.api.seller

import com.dphong.likescash.api.seller.model.SellerDepositRequest
import com.dphong.likescash.common.exception.ResourceNotFoundException
import com.dphong.likescash.common.response.CommonStatus
import com.dphong.likescash.domain.member.Seller
import com.dphong.likescash.domain.seller.SellerDepositOrder
import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.mock.TestContainer
import com.dphong.likescash.repository.SellerDepositOrderRepository
import com.dphong.likescash.repository.member.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SellerDepositServiceTest {

    private lateinit var memberRepository: MemberRepository
    private lateinit var orderRepository: SellerDepositOrderRepository
    private lateinit var service: SellerDepositService


    @BeforeEach
    fun setUp() {
        val testContainer = TestContainer().build()
        memberRepository = testContainer.memberRepository
        orderRepository = testContainer.sellerDepositOrderRepository
        service = SellerDepositService(memberRepository, orderRepository)
    }

    @Test
    fun `예치금을 충전한다`() {
        // given
        val seller = memberRepository.save(MemberFixture.seller()) as Seller
        orderRepository.save(SellerDepositOrder(1L, seller, 1000, "testOrderNumber"))
        val request = SellerDepositRequest("testOrderNumber")

        // when
        val result = service.charge(1L, request)

        // then
        assertThat(result.status).isEqualTo(CommonStatus.SUCCESS)
        assertThat(result.data!!.amount).isEqualTo(1000L)
    }

    @Test
    fun `충전 주문의 판매자 아이디와 로그인한 회원의 아이디가 다르면 예외가 발생한다`() {
        // given
        memberRepository.save(MemberFixture.seller()) as Seller
        val invalidSellerId = -1L
        val request = SellerDepositRequest("testOrderNumber")

        // when
        // then
        assertThatThrownBy { service.charge(invalidSellerId, request) }
            .isInstanceOf(ResourceNotFoundException::class.java)
    }

    @Test
    fun `주문번호로 조회되는 주문이 없으면 예외가 발생한다`() {
        // given
        val seller = memberRepository.save(MemberFixture.seller()) as Seller
        orderRepository.save(SellerDepositOrder(1L, seller, 1000, "testOrderNumber"))
        val otherOrderNumber = "otherOrderNumber"
        val request = SellerDepositRequest(otherOrderNumber)

        // when
        // then
        assertThatThrownBy { service.charge(seller.id!!, request) }
            .isInstanceOf(ResourceNotFoundException::class.java)
    }
}

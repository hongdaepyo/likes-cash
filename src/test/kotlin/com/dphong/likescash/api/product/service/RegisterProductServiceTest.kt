package com.dphong.likescash.api.product.service

import com.dphong.likescash.api.product.model.RegisterProductRequest
import com.dphong.likescash.api.product.model.RegisterProductStatus
import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.mock.TestContainer
import com.dphong.likescash.repository.member.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RegisterProductServiceTest {

    private lateinit var service: RegisterProductService
    private lateinit var memberRepository: MemberRepository

    @BeforeEach
    fun setUp() {
        val container = TestContainer().build()
        service = container.registerProductService
        memberRepository = container.memberRepository
    }

    @Test
    fun `판매자를 찾을 수 없으면 SELLER_NOT_FOUND 상태를 응답한다`() {
        // given
        // when
        val result = service.register(1, RegisterProductRequest("testName"))
        // then
        assertThat(result.status).isEqualTo(RegisterProductStatus.SELLER_NOT_FOUND)
    }

    @Test
    fun `상품을 등록한다`() {
        // given
        val seller = memberRepository.save(MemberFixture.seller())
        // when
        val result = service.register(seller.id!!, RegisterProductRequest("testName"))
        // then
        assertThat(result.status).isEqualTo(RegisterProductStatus.SUCCESS)
    }
}

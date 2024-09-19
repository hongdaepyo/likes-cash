package com.dphong.likescash.api.posts.service

import com.dphong.likescash.api.posts.model.SubmitPostRequest
import com.dphong.likescash.api.posts.model.SubmitPostStatus
import com.dphong.likescash.domain.posts.PostPlatform
import com.dphong.likescash.domain.product.Product
import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.mock.TestContainer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SubmitPostServiceTest {

    private lateinit var testContainer: TestContainer
    private lateinit var service: SubmitPostService

    @BeforeEach
    fun setUp() {
        testContainer = TestContainer().build()
        service = testContainer.submitPostService
    }

    @Test
    fun `게시물 링크를 제출한다`() {
        // given
        val member = MemberFixture.seller(1L)
        testContainer.memberRepository.save(member)
        testContainer.productRepository.save(Product.of("testProduct", MemberFixture.seller()))

        // when
        val result = service.submit(1L, SubmitPostRequest(1L, "www.test.com", PostPlatform.NAVER))

        // then
        assertThat(result.status).isEqualTo(SubmitPostStatus.SUCCESS)
    }

    @Test
    fun `회원이 없으면 MEMBER_NOT_FOUND 상태를 응답한다`() {
        // given
        // when
        val result = service.submit(-1L, SubmitPostRequest(1L, "www.test.com", PostPlatform.NAVER))

        // then
        assertThat(result.status).isEqualTo(SubmitPostStatus.MEMBER_NOT_FOUND)
    }

    @Test
    fun `상품이 없으면 PRODUCT_NOT_FOUND 상태를 응답한다`() {
        // given
        val member = MemberFixture.seller(1L)
        testContainer.memberRepository.save(member)

        // when
        val result = service.submit(member.id!!, SubmitPostRequest(member.id!!, "www.test.com", PostPlatform.NAVER))

        // then
        assertThat(result.status).isEqualTo(SubmitPostStatus.PRODUCT_NOT_FOUND)
    }
}

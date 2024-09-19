package com.dphong.likescash.api.posts.service

import com.dphong.likescash.domain.posts.Post
import com.dphong.likescash.domain.posts.PostPlatform
import com.dphong.likescash.fixtures.MemberFixture
import com.dphong.likescash.fixtures.ProductFixture
import com.dphong.likescash.mock.TestContainer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PostGetterTest {

    @Test
    fun `상품의 게시물을 조회한다`() {
        // given
        val testContainer = TestContainer().build()
        val postRepository = testContainer.postRepository
        val product = ProductFixture.create(1)
        postRepository.save(Post(1L, "www.naver.com", PostPlatform.NAVER, MemberFixture.user(), product))
        postRepository.save(Post(2L, "www.instagram.com", PostPlatform.INSTAGRAM, MemberFixture.user(), product))
        val getter = PostGetter(postRepository)

        // when
        val result = getter.getPostsByProductId(1L)

        // then
        assertThat(result.data).hasSize(2)
    }
}

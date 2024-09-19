package com.dphong.likescash.api.posts.controller

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.api.posts.model.PostDetails
import com.dphong.likescash.api.posts.service.PostGetter
import com.dphong.likescash.common.reponse.DataResult
import com.dphong.likescash.domain.posts.PostReaction
import com.dphong.likescash.mock.FakeAuthentication
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@BaseWebMvcTest([SearchPostController::class])
class SearchPostControllerTest(
    private val mockMvc: MockMvc,
    @MockBean private val postGetter: PostGetter
) {

    @Test
    fun `상품의 게시물을 조회한다`() {
        // given
        SecurityContextHolder.getContext().authentication = FakeAuthentication.MEMBER.authentication
        given(postGetter.getPostsByProductId(1)).willReturn(
            DataResult(listOf(PostDetails(1L, "www.test.com", PostReaction(20))))
        )

        // when
        // then
        mockMvc.get("/v1/posts") {
            param("productId", "1")
        }.andExpect {
            status { isOk() }
            jsonPath("$.data[0].linkUrl") { value("www.test.com") }
            jsonPath("$.data[0].reaction.likesCount") { value(20) }
        }
    }
}

package com.dphong.likescash.api.posts.controller

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.mock.FakeAuthentication
import org.junit.jupiter.api.Test
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@BaseWebMvcTest([SearchPostController::class])
class SearchPostControllerTest(
    private val mockMvc: MockMvc
) {

    @Test
    fun name() {
        SecurityContextHolder.getContext().authentication = FakeAuthentication.MEMBER.authentication
        mockMvc.get("/v1/posts") {
            param("productId", "1")
        }.andExpect {
            status { isOk() }
        }
    }
}

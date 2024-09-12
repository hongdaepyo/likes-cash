package com.dphong.likescash.api.posts.controller

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.api.posts.model.RegisterPostRequest
import com.dphong.likescash.domain.posts.PostPlatform
import com.dphong.likescash.mock.FakeAuthentication
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@BaseWebMvcTest([RegisterPostController::class])
class RegisterPostControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    @Test
    fun name() {
        // given
        SecurityContextHolder.getContext().authentication = FakeAuthentication.User.authentication
        val request = RegisterPostRequest(1, "test", PostPlatform.NAVER)

        // when
        // then
        mockMvc.post("/v1/posts") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isOk() }
        }
    }
}

package com.dphong.likescash.api.posts.controller

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.api.posts.model.SubmitPostRequest
import com.dphong.likescash.api.posts.model.SubmitPostResponse
import com.dphong.likescash.api.posts.model.SubmitPostStatus
import com.dphong.likescash.api.posts.service.SubmitPostService
import com.dphong.likescash.common.reponse.StatusDataResult
import com.dphong.likescash.domain.posts.PostPlatform
import com.dphong.likescash.mock.FakeAuthentication
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@BaseWebMvcTest([SubmitPostController::class])
class SubmitPostControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    @MockBean private val submitPostService: SubmitPostService
) {

    @Test
    fun name() {
        // given
        SecurityContextHolder.getContext().authentication = FakeAuthentication.SELLER.authentication
        val request = SubmitPostRequest(1, "test", PostPlatform.NAVER)
        given(submitPostService.submit(1L, request)).willReturn(
            StatusDataResult(SubmitPostStatus.SUCCESS, SubmitPostResponse(1L))
        )

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

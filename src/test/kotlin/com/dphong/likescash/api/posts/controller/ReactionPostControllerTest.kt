package com.dphong.likescash.api.posts.controller

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.api.posts.model.ReactionPostResponse
import com.dphong.likescash.api.posts.model.ReactionPostStatus
import com.dphong.likescash.api.posts.service.ReactionPostService
import com.dphong.likescash.common.reponse.StatusDataResult
import com.dphong.likescash.mock.FakeAuthentication
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.put

@BaseWebMvcTest([ReactionPostController::class])
class ReactionPostControllerTest(
    private val mockMvc: MockMvc,
    @MockBean private val reactionPostService: ReactionPostService
) {

    @Test
    fun `게시물에 좋아요를 한다`() {
        SecurityContextHolder.getContext().authentication = FakeAuthentication.MEMBER.authentication
        given(reactionPostService.likes(anyLong(), anyLong())).willReturn(
            StatusDataResult(ReactionPostStatus.SUCCESS, ReactionPostResponse(1))
        )

        mockMvc.put("/v1/posts/1/likes")
            .andExpect {
                status { isOk() }
                jsonPath("$.data.likesCount") { value(1) }
            }
    }
}

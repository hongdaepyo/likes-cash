package com.dphong.likescash.api.member.controller

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.api.member.model.SignUpMemberRequest
import com.dphong.likescash.api.member.model.SignUpMemberResponse
import com.dphong.likescash.api.member.model.SignUpMemberStatus
import com.dphong.likescash.api.member.service.SignUpMemberService
import com.dphong.likescash.common.response.StatusDataResult
import com.dphong.likescash.domain.member.MemberRole
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@BaseWebMvcTest([SignUpMemberController::class])
class SignUpMemberControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    @MockBean private val signUpMemberService: SignUpMemberService
) {
    @Test
    fun `회원 가입을 성공한다`() {
        // given
        val request = SignUpMemberRequest("testUsername", "testPassword", "testName", MemberRole.USER)
        val result = StatusDataResult(
            SignUpMemberStatus.SUCCESS, SignUpMemberResponse(1L, "testUsername", "testName")
        )
        given(signUpMemberService.signUp(request)).willReturn(result)

        mockMvc.post("/v1/member/sign-up") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isOk() }
            jsonPath("$.status") {
                value(SignUpMemberStatus.SUCCESS.toString())
            }
        }
    }
}

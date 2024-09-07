package com.dphong.likescash.api.member

import com.dphong.likescash.common.config.auth.SecurityConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Import(SecurityConfig::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@WebMvcTest(SignUpMemberController::class)
class SignUpMemberControllerTest(
    private val mockMvc: MockMvc
) {
    @Test
    fun `회원 가입을 성공한다`() {
        mockMvc.perform(
            post("/v1/member/sign-up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect { status().isOk }
    }
}

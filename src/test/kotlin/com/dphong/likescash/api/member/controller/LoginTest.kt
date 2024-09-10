package com.dphong.likescash.api.member.controller

import com.dphong.likescash.domain.Member
import com.dphong.likescash.repository.member.MemberRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest(value = ["jwt.secret=valid_secret_key"])
@AutoConfigureMockMvc
class LoginTest(
    private val mockMvc: MockMvc,
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Test
    fun `회원 로그인을 성공한다`() {
        // given
        val username = "testUsername"
        val password = "testPassword"
        memberRepository.save(Member(username, passwordEncoder.encode(password), "test"))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/login")
                .formField("username", username)
                .formField("password", password)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        ).andExpect { MockMvcResultMatchers.status().isOk }
    }
}

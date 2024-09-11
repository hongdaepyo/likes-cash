package com.dphong.likescash.common.config.web

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.common.annotation.LoginMember
import com.dphong.likescash.common.config.auth.MemberFacade
import com.dphong.likescash.mock.FakeAuthentication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@BaseWebMvcTest([LoginMemberTestController::class])
class LoginMemberArgumentResolverTest(
    private val mockMvc: MockMvc
) {

    @Test
    fun `로그인한 회원이면 회원 정보를 주입받는다`() {
        SecurityContextHolder.getContext().authentication = FakeAuthentication.MEMBER.authentication
        mockMvc.get("/local/test")
            .andExpect {
                status { isOk() }
                jsonPath("$") {
                    value("testName")
                }
            }
    }

    @Test
    fun `로그인하지 않은 회원이면 예외가 발생한다`() {
        runCatching {
            mockMvc.get("/local/test")
        }.onFailure {
            assertThat(it.message).contains("로그인한 사용자가 아닙니다.")
        }
    }
}

@RestController
class LoginMemberTestController {

    @GetMapping("/local/test")
    fun test(@LoginMember member: MemberFacade) = member.username
}

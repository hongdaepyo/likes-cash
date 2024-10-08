package com.dphong.likescash.api.member.service

import com.dphong.likescash.api.member.model.SignUpMemberRequest
import com.dphong.likescash.api.member.model.SignUpMemberStatus
import com.dphong.likescash.domain.member.MemberRole
import com.dphong.likescash.mock.FakeMemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SignUpMemberServiceTest {

    private lateinit var service: SignUpMemberService
    private lateinit var memberRepository: FakeMemberRepository

    @BeforeEach
    fun setUp() {
        memberRepository = FakeMemberRepository()
        service = SignUpMemberService(memberRepository, BCryptPasswordEncoder())
    }

    @Test
    fun `이미 가입된 username이면 USERNAME_ALREADY_EXISTS를 반환한다`() {
        // given
        val request = SignUpMemberRequest("testUsername", "testPassword", "testName", MemberRole.USER)
        service.signUp(request)

        // when
        val response = service.signUp(request)

        // then
        assertThat(response.status).isEqualTo(SignUpMemberStatus.USERNAME_ALREADY_EXISTS)
    }
}

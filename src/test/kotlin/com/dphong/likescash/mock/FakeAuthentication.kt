package com.dphong.likescash.mock

import com.dphong.likescash.common.config.auth.MemberAuthenticationToken
import com.dphong.likescash.common.config.auth.MemberDetails
import com.dphong.likescash.domain.member.MemberRole
import org.springframework.security.core.Authentication

enum class FakeAuthentication(val authentication: Authentication) {
    MEMBER(MemberAuthenticationToken(MemberDetails(1L, "testName", "testEmail", MemberRole.USER))),
    SELLER(MemberAuthenticationToken(MemberDetails(1L, "testName", "testEmail", MemberRole.SELLER))),
    ADMIN(MemberAuthenticationToken(MemberDetails(1L, "testName", "testEmail", MemberRole.ADMIN))),
}

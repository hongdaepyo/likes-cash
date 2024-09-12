package com.dphong.likescash.fixtures

import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.member.MemberRole

object MemberFixture {

    fun seller() = Member(null, "testName", "testPassword", "testName", MemberRole.SELLER)
}

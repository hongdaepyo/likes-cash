package com.dphong.likescash.fixtures

import com.dphong.likescash.domain.Member
import com.dphong.likescash.domain.MemberRole

object MemberFixture {

    fun seller() = Member(null, "testName", "testPassword", "testName", MemberRole.SELLER)
}

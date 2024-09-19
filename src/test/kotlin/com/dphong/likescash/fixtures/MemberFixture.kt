package com.dphong.likescash.fixtures

import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.member.MemberRole

object MemberFixture {

    fun user(id: Long? = null) = Member(id, "testName", "testPassword", "testName", MemberRole.USER)
    fun seller(id: Long? = null) = Member(id, "testName", "testPassword", "testName", MemberRole.SELLER)
}

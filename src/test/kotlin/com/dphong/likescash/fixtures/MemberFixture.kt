package com.dphong.likescash.fixtures

import com.dphong.likescash.domain.member.Seller
import com.dphong.likescash.domain.member.User

object MemberFixture {

    fun user(id: Long? = null) = User(id, "testName", "testPassword", "testName")
    fun seller(id: Long? = null) = Seller(id, "testName", "testPassword", "testName")
}

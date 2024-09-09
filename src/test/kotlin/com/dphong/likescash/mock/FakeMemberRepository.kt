package com.dphong.likescash.mock

import com.dphong.likescash.domain.Member
import com.dphong.likescash.repository.member.MemberRepository

class FakeMemberRepository: MemberRepository {
    private val data = mutableListOf<Member>()

    override fun findByUsernameOrNull(username: String): Member? {
        return data.firstOrNull { it.username == username  }
    }
}

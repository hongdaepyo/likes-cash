package com.dphong.likescash.repository.member

import com.dphong.likescash.domain.Member

interface MemberRepository {
    fun findByUsernameOrNull(username: String): Member?
}

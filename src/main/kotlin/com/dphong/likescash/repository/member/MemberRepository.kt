package com.dphong.likescash.repository.member

import com.dphong.likescash.domain.Member
import com.dphong.likescash.domain.MemberRole

interface MemberRepository {
    fun findByUsernameOrNull(username: String): Member?
    fun save(member: Member): Member
    fun findByIdAndRole(id: Long, role: MemberRole): Member?
}

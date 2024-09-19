package com.dphong.likescash.repository.member

import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.member.MemberRole

interface MemberRepository {
    fun findByUsernameOrNull(username: String): Member?
    fun save(member: Member): Member
    fun findByIdAndRole(id: Long, role: MemberRole): Member?
    fun findByIdOrNull(id: Long): Member?
}

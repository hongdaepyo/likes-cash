package com.dphong.likescash.repository.member

import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.member.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, Long> {

    fun findByUsername(username: String): Member?
    fun findByIdAndRole(id: Long, role: MemberRole): Member?
}

package com.dphong.likescash.repository.member

import com.dphong.likescash.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository: JpaRepository<Member, Long> {

    fun findByUsername(username: String): Member?
}

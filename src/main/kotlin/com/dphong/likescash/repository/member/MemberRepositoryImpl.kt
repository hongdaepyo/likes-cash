package com.dphong.likescash.repository.member

import com.dphong.likescash.domain.Member
import com.dphong.likescash.domain.MemberRole
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {

    override fun findByUsernameOrNull(username: String): Member? =
        memberJpaRepository.findByUsername(username)

    override fun save(member: Member): Member {
        return memberJpaRepository.save(member)
    }

    override fun findByIdAndRole(id: Long, role: MemberRole): Member? {
        return memberJpaRepository.findByIdAndRole(id, role)
    }
}

package com.dphong.likescash.repository.member

import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.member.MemberRole
import org.springframework.data.repository.findByIdOrNull
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

    override fun findByIdOrNull(id: Long): Member? {
        return memberJpaRepository.findByIdOrNull(id)
    }
}

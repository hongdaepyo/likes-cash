package com.dphong.likescash.repository.member

import com.dphong.likescash.domain.Member
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {

    override fun findByUsernameOrNull(username: String): Member? = memberJpaRepository.findByUsername(username)
    override fun save(member: Member): Member {
        return memberJpaRepository.save(member)
    }
}

package com.dphong.likescash.mock

import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.member.MemberRole
import com.dphong.likescash.repository.member.MemberRepository

class FakeMemberRepository : MemberRepository, AbstractFakeRepository<Member>() {

    override fun findByUsernameOrNull(username: String): Member? {
        return data.firstOrNull { it.username == username }
    }

    override fun findByIdAndRole(id: Long, role: MemberRole): Member? {
        return data.firstOrNull { it.id == id && it.role == role }
    }

    override fun findByIdOrNull(id: Long): Member? {
        return data.firstOrNull { it.id == id }
    }

    override fun save(member: Member): Member =
        if (member.id == null || member.id == 0L) {
            val newMember = Member(
                autoGeneratedId.incrementAndGet(),
                member.username,
                member.password,
                member.name,
                member.role
            )

            data.add(newMember)
            newMember
        } else {
            data.removeIf { it.id == member.id }
            data.add(member)
            member
        }
}

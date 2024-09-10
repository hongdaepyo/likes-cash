package com.dphong.likescash.api.member.model

import com.dphong.likescash.domain.Member

data class SignUpMemberResponse(
    val id: Long?,
    val username: String,
    val name: String
) {

    companion object {
        fun of(member: Member) = SignUpMemberResponse(
            member.id,
            member.username,
            member.name.orEmpty()
        )
    }
}

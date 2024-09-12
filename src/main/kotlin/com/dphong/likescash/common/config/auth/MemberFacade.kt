package com.dphong.likescash.common.config.auth

import com.dphong.likescash.domain.member.MemberRole

data class MemberFacade(val memberDetails: MemberDetails) {
    val memberId: Long get() = memberDetails.getId()
    val username: String get() = memberDetails.getUsername()
    val role: MemberRole? get() = memberDetails.authorities.firstOrNull()
            ?.authority
            ?.let { MemberRole.valueOf(it) }
}

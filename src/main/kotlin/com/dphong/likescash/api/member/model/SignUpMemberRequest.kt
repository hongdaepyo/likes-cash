package com.dphong.likescash.api.member.model

import com.dphong.likescash.domain.member.MemberRole

data class SignUpMemberRequest(val username: String, val password: String, val name: String, val role: MemberRole)

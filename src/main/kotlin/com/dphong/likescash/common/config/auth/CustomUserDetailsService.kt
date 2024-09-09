package com.dphong.likescash.common.config.auth

import com.dphong.likescash.repository.member.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        return memberRepository.findByUsernameOrNull(username)
            ?.let { MemberDetails(it.id!!, it.username, it.password, it.role) }
    }
}

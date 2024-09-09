package com.dphong.likescash.common.config.auth

import com.dphong.likescash.domain.MemberRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberDetails(
    private val id: Long,
    private val username: String,
    private val password: String = "",
    private val role: MemberRole
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(role.name))

    fun getId(): Long = id

    override fun getPassword(): String = password

    override fun getUsername(): String = username
}

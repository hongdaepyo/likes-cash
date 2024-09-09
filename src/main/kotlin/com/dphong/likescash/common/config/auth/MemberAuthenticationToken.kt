package com.dphong.likescash.common.config.auth

import org.springframework.security.authentication.AbstractAuthenticationToken

class MemberAuthenticationToken(
    private val memberDetails: MemberDetails
): AbstractAuthenticationToken(memberDetails.authorities) {
    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any = memberDetails

    override fun isAuthenticated(): Boolean = true

    fun getMemberId(): Long = memberDetails.getId()
}

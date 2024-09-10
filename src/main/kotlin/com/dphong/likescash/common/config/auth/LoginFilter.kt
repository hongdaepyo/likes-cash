package com.dphong.likescash.common.config.auth

import com.dphong.likescash.common.utils.JwtTokenUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class LoginFilter(
    authenticationManager: AuthenticationManager,
    private val tokenUtil: JwtTokenUtil
) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val username = obtainUsername(request)
        val password = obtainPassword(request)
        val token = UsernamePasswordAuthenticationToken.unauthenticated(username, password)
        return authenticationManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val memberDetails = authResult.principal as MemberDetails
        val username = memberDetails.username
        val role = memberDetails.authorities.firstOrNull()?.authority
        val memberId = memberDetails.getId()
        val token = createJwtToken(memberId, username, role)

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer $token")
    }

    private fun createJwtToken(memberId: Long, username: String, role: String?) =
        tokenUtil.generateToken(memberId, username, role.orEmpty())

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        failed: AuthenticationException?
    ) {
        response.sendError(HttpStatus.UNAUTHORIZED.value())
    }
}

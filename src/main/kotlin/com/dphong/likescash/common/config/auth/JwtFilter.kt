package com.dphong.likescash.common.config.auth

import com.dphong.likescash.common.utils.JwtTokenUtil
import com.dphong.likescash.domain.MemberRole
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(private val tokenUtil: JwtTokenUtil): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = extractToken(request)
        if (token.isNotBlank() && tokenUtil.validateToken(token)) {
            val id = tokenUtil.getIdFromToken(token).toLong()
            val username = tokenUtil.getClaimFromToken(token, "username")
            val role = tokenUtil.getClaimFromToken(token, "role")

            SecurityContextHolder.getContext().authentication =
                MemberAuthenticationToken(
                    MemberDetails(id = id, username = username, role = MemberRole.valueOf(role))
                )
        }

        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest) =
        request.getHeader(HttpHeaders.AUTHORIZATION)
            ?.substringAfter("Bearer ")
            .orEmpty()
}

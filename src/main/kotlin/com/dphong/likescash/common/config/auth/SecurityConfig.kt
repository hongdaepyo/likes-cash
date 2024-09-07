package com.dphong.likescash.common.config.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler

@EnableWebSecurity
@Configuration
class SecurityConfig {

    companion object {
        val PERMIT_ALL_PATTERNS: Array<String> = arrayOf("/v1/member/sign-up")
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http.httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling {
                it.accessDeniedHandler(JwtAccessDeniedHandler())
                    .authenticationEntryPoint(JwtAuthenticationEntryPoint())
            }
            .authorizeHttpRequests {
                it.requestMatchers(*PERMIT_ALL_PATTERNS).permitAll()
                    .anyRequest().authenticated()
            }
            .build()
}

class JwtAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException?
    ) {
        response.sendError(HttpStatus.UNAUTHORIZED.value())
    }
}

class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        response.sendError(HttpStatus.UNAUTHORIZED.value())
    }
}

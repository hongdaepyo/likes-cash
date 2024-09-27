package com.dphong.likescash.common.config.auth

import com.dphong.likescash.common.utils.JwtTokenUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    @Value("\${jwt.secret}") private val secret: String,
    private val authenticationConfiguration: AuthenticationConfiguration
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun authenticationManger(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            httpBasic { disable() }
            csrf { disable() }
            cors { disable() }
            formLogin { disable() }
            exceptionHandling {
                accessDeniedHandler = JwtAccessDeniedHandler()
                authenticationEntryPoint = JwtAuthenticationEntryPoint()
            }
            authorizeHttpRequests {
                authorize("/v1/member/sign-up", permitAll)
                authorize(HttpMethod.GET, "/v1/products/**", permitAll)
                authorize(HttpMethod.GET, "/healthcheck", permitAll)
                authorize(anyRequest, authenticated)
            }
            addFilterBefore<LoginFilter>(JwtFilter(jwtTokenUtil()))
            addFilterAt<UsernamePasswordAuthenticationFilter>(LoginFilter(authenticationManger(), jwtTokenUtil()))
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
        }

        return http.build()
    }

    @Bean
    fun loginFilter(): LoginFilter = LoginFilter(authenticationManger(), jwtTokenUtil())

    @Bean
    fun jwtTokenUtil(): JwtTokenUtil = JwtTokenUtil(secret)
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

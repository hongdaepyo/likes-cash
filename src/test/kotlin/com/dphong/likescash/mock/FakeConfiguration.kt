package com.dphong.likescash.mock

import com.dphong.likescash.common.config.auth.CustomUserDetailsService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain

@TestConfiguration
class FakeConfiguration {

    @Bean
    fun userDetailsService(): UserDetailsService = CustomUserDetailsService(FakeMemberRepository())

    @Bean
    fun securityChainFilter(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            authorizeHttpRequests {
                authorize("/v1/member/sign-up", permitAll)
                authorize(HttpMethod.GET, "/v1/products/**", permitAll)
                authorize(anyRequest, authenticated)
            }
        }
        return http.build()
    }
}

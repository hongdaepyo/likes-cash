package com.dphong.likescash.mock

import com.dphong.likescash.common.config.auth.CustomUserDetailsService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetailsService

@TestConfiguration
class FakeConfiguration {

    @Bean
    fun userDetailsService(): UserDetailsService = CustomUserDetailsService(FakeMemberRepository())
}

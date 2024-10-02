package com.dphong.likescash

import com.dphong.likescash.common.config.JpaConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Import(JpaConfig::class)
@ActiveProfiles("test")
@Transactional
@SpringBootTest
class IntegrationTest

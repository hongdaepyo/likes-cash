package com.dphong.likescash.common.config

import com.dphong.likescash.BASE_PACKAGE
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(BASE_PACKAGE)
@EnableJpaRepositories(BASE_PACKAGE)
@EnableJpaAuditing
@Configuration
class JpaConfig(
    @PersistenceContext private val entityManager: EntityManager
) {

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory = JPAQueryFactory(entityManager)
}

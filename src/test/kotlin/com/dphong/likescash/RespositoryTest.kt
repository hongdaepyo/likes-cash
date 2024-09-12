package com.dphong.likescash

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Import(TestJpaConfig::class)
@ActiveProfiles("test")
@DataJpaTest
abstract class RepositoryTest {
    @Autowired lateinit var entityManager: TestEntityManager
}

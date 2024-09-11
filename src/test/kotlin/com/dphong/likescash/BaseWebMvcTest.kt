package com.dphong.likescash

import com.dphong.likescash.common.config.web.WebConfig
import com.dphong.likescash.mock.FakeConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.core.annotation.AliasFor
import org.springframework.test.context.TestConstructor
import kotlin.reflect.KClass

@Import(FakeConfiguration::class, WebConfig::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@WebMvcTest
annotation class BaseWebMvcTest(
    @get:AliasFor(annotation = WebMvcTest::class, attribute = "value")
    val value: Array<KClass<*>> = [],
    @get:AliasFor(annotation = WebMvcTest::class, attribute = "controllers")
    val controllers: Array<KClass<*>> = []
)

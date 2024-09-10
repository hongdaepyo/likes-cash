package com.dphong.likescash.common.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class SecretKeyGeneratorTest {

    @Test
    fun generate() {
        val secret = SecretKeyGenerator.generate("dphong")
        assertThat(secret).isNotBlank
    }
}

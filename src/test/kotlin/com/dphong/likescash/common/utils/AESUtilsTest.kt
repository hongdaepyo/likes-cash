package com.dphong.likescash.common.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AESUtilsTest {

    @ParameterizedTest
    @CsvSource("test1", "test2", "test3")
    fun `AES 암복호화를 성공한다`(input: String) {
        val encryptedText = AESUtils.encrypt(input)
        assertThat(encryptedText).isNotBlank

        val decryptedText = AESUtils.decrypt(encryptedText)
        assertThat(decryptedText).isEqualTo(input)
    }
}

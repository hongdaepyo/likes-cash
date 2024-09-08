package com.dphong.likescash.common.utils

import com.nimbusds.jose.util.Base64
import java.nio.charset.StandardCharsets
import java.security.SecureRandom

object SecretKeyGenerator {

    fun generate(seed: String): String {
        val bytes = ByteArray(32)
        SecureRandom(seed.toByteArray(StandardCharsets.UTF_8)).nextBytes(bytes)
        return Base64.encode(bytes).toString()
    }
}

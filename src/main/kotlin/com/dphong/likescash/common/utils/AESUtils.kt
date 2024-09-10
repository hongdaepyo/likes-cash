package com.dphong.likescash.common.utils

import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

object AESUtils {
    private const val ALGORITHM = "AES/GCM/NoPadding"
    private const val KEY = "Y29tLmRwaG9uZy5saWtlc2Nhc2g="
    private const val GCM_IV_LENGTH = 16
    private const val GCM_IV_TAG = 128

    fun encrypt(plainText: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        val iv = generateIv()
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), generateGcmParams(iv))
        val cipherText = cipher.doFinal(plainText.toByteArray())
        return Base64.getEncoder().encodeToString(iv + cipherText)
    }

    fun decrypt(encryptedText: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        val decode = Base64.getDecoder().decode(encryptedText)
        val iv = decode.copyOfRange(0, GCM_IV_LENGTH)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), generateGcmParams(iv))
        val cipherText = decode.copyOfRange(GCM_IV_LENGTH, decode.size)
        val plainText = cipher.doFinal(cipherText)
        return String(plainText)
    }


    private fun generateIv(): ByteArray = ByteArray(GCM_IV_LENGTH).also { SecureRandom().nextBytes(it) }

    private fun generateGcmParams(iv: ByteArray): GCMParameterSpec = GCMParameterSpec(GCM_IV_TAG, iv)

    private fun getSecretKey(): SecretKey = SecretKeySpec(KEY.toByteArray().copyOf(16), "AES")
}

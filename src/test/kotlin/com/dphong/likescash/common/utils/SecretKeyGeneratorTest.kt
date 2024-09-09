package com.dphong.likescash.common.utils

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.jwk.OctetSequenceKey
import com.nimbusds.jose.jwk.gen.OctetSequenceKeyGenerator
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


class SecretKeyGeneratorTest {

    @Test
    fun generate() {
        val secret = SecretKeyGenerator.generate("dphong")
        assertThat(secret).isNotBlank

//        val secret2 = SecretKeyGenerator.generate2()
//        println("secret2 = ${secret2}")
//        assertThat(secret2).isNotBlank

        val hmacKey: SecretKey = KeyGenerator.getInstance("HmacSha256").generateKey()

        val generate = OctetSequenceKey.Builder(hmacKey)
            .keyID("dphong")
            .algorithm(JWSAlgorithm.HS256)
            .issueTime(Date())
            .build()

        println(generate)

        val keyPair = OctetSequenceKeyGenerator(256)
            .keyID("dphong")
            .algorithm(JWSAlgorithm.HS256)
            .issueTime(Date())
            .generate()

        println(keyPair.keyValue)
        val signer = MACSigner(keyPair)
        val claimSet = JWTClaimsSet.Builder()
            .issuer("com.dphong")
            .issueTime(Date())
            .jwtID("jwtID")
            .claim("testKey", "testValue")
            .build()
        val signedJWT = SignedJWT(JWSHeader(JWSAlgorithm.HS256), claimSet)
        signedJWT.sign(signer)
        println(signedJWT.serialize())



        val jwt =
            SignedJWT.parse("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjb20uZHBob25nIiwidGVzdEtleSI6InRlc3RWYWx1ZSIsImlhdCI6MTcyNTkzMjAzNSwianRpIjoiand0SUQifQ.AQqLlgM-NgjTNGrqWkWbP0bGhLGF9AY5CcRznYfhR9w")

        println("jwt = ${jwt.jwtClaimsSet}")

        val pair = KeyPairGenerator.getInstance("RSA").genKeyPair()

        val private = Base64.getEncoder().encodeToString(pair.private.encoded)
        println(private)
        val public = Base64.getEncoder().encodeToString(pair.public.encoded)
        println(public)

        val publicKey = KeyFactory.getInstance("RSA")
            .generatePublic(
                X509EncodedKeySpec(
                    Base64.getDecoder().decode(
                        "MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAroni3JiLPKZFOfGJ0ASwVevh+8J0y+54x0iRT0YAM6NeA/V6B+hRU+MmUaZ4Dne0p+U+7NlEFYGvKUwdLFrOKdLteBUila/IJk9TUcGRq9qnhNDMqL8NVbC/oi0sYtYrYVzKz8GBS6iw++qFlRgzAjXe331cFC8M7nMnFEX4/7B6RXYqQOR3jqu47E+KNqvfNyt8gMdEyJPyGLpkkr+2Se+qtYRzlqShRvpP+PkfZe46apgJKiR/KMHFHg0Cl1A3bd6v+4pxn4RoeEqgBC9can122miFxOTGGPtR6QM7XW6MRtyFvNTIA1HBQmLIPrP4ss2wxStweLDM6XU53h6/NxF6BdxudE2QUSXV+MunJMv8UDCFdzwAzp+rSuodf9Yd4/uDM2Rm/4zi3bp7dNRdqPvrhaFm8OYKXy5hwpG0xAtocjyzD2YCmGtljVoSxQfXOjiyhaUNliwp8HUnkwgQNooWMK1xAIb745DLBPvhwEbmHlRpnJqSw9hf7QruqMCzAgMBAAE="
                    )
                )
            )

        val privateKey = KeyFactory.getInstance("RSA")
            .generatePrivate(
                PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(
                        "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"
                    )
                )
            )

        println("publicKey = ${publicKey}")
        println("privateKey = ${privateKey}")


    }
}

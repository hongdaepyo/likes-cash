package com.dphong.likescash.common.utils

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class JwtTokenUtil(private val secret: String) {

    fun generateToken(id: Long, username: String, role: String): String =
        generate(
            JWTClaimsSet.Builder()
                .subject("likesCash")
                .claim("id", AESUtils.encrypt(id.toString()))
                .claim("username", username)
                .claim("role", role)
                .issuer("com.dphong.likesCash")
                .issueTime(Date.from(Instant.now()))
                .expirationTime(getExpirationTime())
                .build()
        )

    fun validateToken(token: String): Boolean {
        val signedJWT = SignedJWT.parse(token)
        val verifier = MACVerifier(secret)
        // TODO: 토큰 검증시 예외처리
        return signedJWT.verify(verifier)
    }

    fun getClaimFromToken(token: String, key: String): String = getClaimsFromToken(token).claims[key].toString()

    fun getIdFromToken(token: String): String = AESUtils.decrypt(getClaimFromToken(token, "id"))

    private fun getClaimsFromToken(token: String): JWTClaimsSet = SignedJWT.parse(token).jwtClaimsSet

    private fun getExpirationTime(): Date? = Date.from(Instant.now().plus(7, ChronoUnit.DAYS))

    private fun generate(claimsSet: JWTClaimsSet): String {
        val signer = MACSigner(secret)
        val signedJWT = SignedJWT(JWSHeader(JWSAlgorithm.HS256), claimsSet)
        signedJWT.sign(signer)
        return signedJWT.serialize()
    }
}

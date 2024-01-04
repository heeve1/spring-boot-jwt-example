package com.example.demo

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {

    private var key: SecretKey = Jwts.SIG.HS256.key().build()

    fun generateJwt(): String {
        val now = Date()

        return Jwts.builder()
            .issuer("issuer")
            .issuedAt(Date())
            .subject("subject")
            .expiration(Date(now.time + Duration.ofMinutes(30).toMillis()))
            .claim("id", "1")
            .signWith(key)
            .compact() ?: throw IllegalStateException("Failed to generate JWT")
    }

    fun verifyJwt(header: String): Long {
        val prefix = "bearer "

        if (header.startsWith(prefix)) {
            val jwt = header.substring(prefix.length)

            return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .payload
                .get("id")
                .toString()
                .toLong()
        }

        throw IllegalArgumentException("Input string does not start with 'bearer '.")
    }
}
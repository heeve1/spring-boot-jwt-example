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
}
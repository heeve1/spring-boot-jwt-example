package com.example.demo.login

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class SessionService(private val expiredSessionRepository: ExpiredSessionRepository) {

    fun expireSession(jwt: String) {
        expiredSessionRepository.save(ExpiredSession(jwt))
    }

    fun verify(jwt: String): Boolean {
        if (expiredSessionRepository.existsByToken(jwt)) {
            throw IllegalStateException("Expired token")
        }
        return true
    }
}

@Entity
class ExpiredSession(val token: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}

interface ExpiredSessionRepository : JpaRepository<ExpiredSession, Long> {
    fun existsByToken(token: String): Boolean
}
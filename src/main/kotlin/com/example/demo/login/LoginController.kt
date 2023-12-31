package com.example.demo.login

import com.example.demo.JwtService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private var jwtService: JwtService,
    private val sessionService: SessionService
) {

    @GetMapping("login")
    fun login(): String {

        // FIXME id/pw 검증은 한 것으로 가정

        return jwtService.generateJwt()
    }

    @GetMapping("logout")
    fun logout(
        @RequestHeader("Authorization") header: String
    ) {
        sessionService.expireSession(header.substring("bearer ".length))
    }
}
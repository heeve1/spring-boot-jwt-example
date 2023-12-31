package com.example.demo.login

import com.example.demo.JwtService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(private var jwtService: JwtService) {

    @GetMapping("login")
    fun login(): String {

        // FIXME id/pw 검증은 한 것으로 가정

        return jwtService.generateJwt()
    }
}
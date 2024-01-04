package com.example.demo.users

import com.example.demo.JwtService
import com.example.demo.login.SessionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val jwtService: JwtService,
    private val sessionService: SessionService

) {

    @GetMapping("users")
    fun getUsers(
        @RequestHeader("Authorization") header: String
    ): String {
        sessionService.verify(header.substring("bearer ".length))

        val userId = jwtService.verifyJwt(header)

        return userService.getUsers(userId)
    }
}
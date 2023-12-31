package com.example.demo.users

import com.example.demo.JwtService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val jwtService: JwtService
) {

    @GetMapping("users")
    fun getUsers(
        @RequestHeader("Authorization") header: String
    ): String {
        val userId = jwtService.verifyJwt(header)

        return userService.getUsers(userId)
    }
}
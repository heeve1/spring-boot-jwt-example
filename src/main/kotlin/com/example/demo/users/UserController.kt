package com.example.demo.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("users")
    fun getUsers(): String = userService.getUsers(1L)
}
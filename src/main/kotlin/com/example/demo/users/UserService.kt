package com.example.demo.users

import org.springframework.stereotype.Service

@Service
class UserService {

    val users: Map<Long, String> = mapOf(1L to "heeve")

    fun getUsers(userId: Long): String = users[userId] ?: throw IllegalStateException("User not found")
}
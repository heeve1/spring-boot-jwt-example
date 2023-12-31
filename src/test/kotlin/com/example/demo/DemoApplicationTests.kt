package com.example.demo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    val testRestTemplate = TestRestTemplate()

    @LocalServerPort
    var randomServerPort: Int = 0

    @Test
    fun `유저정보를 가져온다`() {
        val users = testRestTemplate.getForObject("http://localhost:$randomServerPort/users", String::class.java)
        assertEquals("heeve", users)
    }

    @Test
    fun `로그인을 요청하는 경우 JWT 토큰이 응답된다`() {
        val jwt = testRestTemplate.getForObject("http://localhost:$randomServerPort/login", String::class.java)
        assertNotNull(jwt)
    }
}
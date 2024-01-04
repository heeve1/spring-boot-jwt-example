package com.example.demo

import org.junit.jupiter.api.Assertions.assertEquals
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
}

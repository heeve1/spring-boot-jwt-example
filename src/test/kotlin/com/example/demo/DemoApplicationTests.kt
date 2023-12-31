package com.example.demo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    val testRestTemplate = TestRestTemplate()

    @LocalServerPort
    var randomServerPort: Int = 0

    @Test
    fun `jwt를 이용해서 유저정보를 가져온다`() {

        // #1 로그인을 요청해서 JWT 토큰을 받는다.
        val jwt = testRestTemplate.getForObject("http://localhost:$randomServerPort/login", String::class.java)

        // #2 JWT 토큰을 이용해서 유저정보를 가져온다.
        val header = HttpHeaders()
        header.set("Authorization", "bearer $jwt")
        val entity = HttpEntity<String>(header)
        val response = testRestTemplate.exchange<String>("http://localhost:$randomServerPort/users", HttpMethod.GET, entity)

        assertEquals("heeve", response.body)
    }

    @Test
    fun `로그인을 요청하는 경우 JWT 토큰이 응답된다`() {
        val jwt = testRestTemplate.getForObject("http://localhost:$randomServerPort/login", String::class.java)
        assertNotNull(jwt)
    }
}
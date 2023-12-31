package com.example.demo

import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.*
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

    @Test
    @Transactional
    fun `로그아웃 처리한 토큰으로는 유저 정보를 얻을 수 없다`() {
        // #1 로그인을 요청해서 JWT 토큰을 받는다.
        val jwt = testRestTemplate.getForObject("http://localhost:$randomServerPort/login", String::class.java)

        // #2 로그아웃
        val header = HttpHeaders()
        header.set("Authorization", "bearer $jwt")
        val entity = HttpEntity<String>(header)
        testRestTemplate.exchange<String>("http://localhost:$randomServerPort/logout", HttpMethod.GET, entity)

        // #3 로그아웃 처리한 토큰으로는 유저 정보를 얻을 수 없다.
        val response2 =
            testRestTemplate.exchange<String>("http://localhost:$randomServerPort/users", HttpMethod.GET, entity)
        assertTrue(response2.statusCode.isError)
    }
}
package com.housebills.categories

import com.housebills.BaseIntegrationTest
import com.housebills.categories.dtos.CategoryOutDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


internal class CategoryControllerTest(@Autowired val restTemplate: TestRestTemplate) : BaseIntegrationTest() {
    val BASE_PATH = "/categories"

    @Test
    @Order(1)
    fun `Get all elements and receive empty result`() {
        val entity = restTemplate.getForEntity<String>(BASE_PATH)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).isEqualTo("[]")
    }

    @Test
    @Order(1)
    fun `Get one element and receive a not found`() {
        val entity = restTemplate.getForEntity<String>("$BASE_PATH/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    @Order(2)
    fun `Post two elements`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entityOne =
            restTemplate.postForEntity<CategoryOutDto>(BASE_PATH, HttpEntity("""{"name": "Revenue"}""", headers))
        assertThat(entityOne.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(entityOne.body).isEqualTo(CategoryOutDto(1, "Revenue"))

        val entityTwo =
            restTemplate.postForEntity<CategoryOutDto>(BASE_PATH, HttpEntity("""{"name": "Expense"}""", headers))
        assertThat(entityTwo.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(entityTwo.body).isEqualTo(CategoryOutDto(2, "Expense"))
    }

    @Test
    @Order(3)
    fun `Get all elements and receive the previous two inserted elements`() {
        val entity = restTemplate.getForEntity<String>(BASE_PATH)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        // TODO: Add a converter from CategoryOutDto to JSON here
        assertThat(entity.body).isEqualTo("""[{"id":1,"name":"Revenue"},{"id":2,"name":"Expense"}]""")
    }

    @Test
    @Order(3)
    fun `Get one element`() {
        val entity = restTemplate.getForEntity<CategoryOutDto>("$BASE_PATH/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).isEqualTo(CategoryOutDto(1, "Revenue"))
    }
}
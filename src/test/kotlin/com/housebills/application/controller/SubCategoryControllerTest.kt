package com.housebills.application.controller

import com.housebills.BaseIntegrationTest
import com.housebills.application.dto.category.CategoryOutDto
import com.housebills.application.dto.sub.category.SubCategoryOutDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

internal class SubCategoryControllerTest(@Autowired val restTemplate: TestRestTemplate) : BaseIntegrationTest() {
    val BASE_PATH = "/sub-categories"

    private fun createCategory() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        restTemplate.postForEntity<CategoryOutDto>("/categories", HttpEntity("""{"name": "Revenue"}""", headers))
    }

    @BeforeAll
    internal fun beforeAll() {
        createCategory()
    }

    @Test
    @Order(1)
    fun `Get all elements and receive empty result`() {
        val entity = restTemplate.getForEntity<String>(BASE_PATH)
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).isEqualTo("[]")
    }

    @Test
    @Order(1)
    fun `Get one element and receive a not found`() {
        val entity = restTemplate.getForEntity<String>("$BASE_PATH/1")
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    @Order(1)
    fun `Update one element and receive a not found`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity = restTemplate.exchange<String>(
            "$BASE_PATH/1",
            HttpMethod.PUT,
            HttpEntity("""{"name": "Saves"}""", headers)
        )
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    @Order(1)
    fun `Delete one element and receive a not found`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity = restTemplate.exchange<String>(
            "$BASE_PATH/1",
            HttpMethod.DELETE,
            HttpEntity(null, headers)
        )
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    @Order(2)
    fun `Post element with an invalid category`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity =
            restTemplate.postForEntity<String>(
                BASE_PATH,
                HttpEntity("""{"name": "Market", "categoryId": 100}""", headers)
            )
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    @Order(3)
    fun `Post two elements`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entityOne =
            restTemplate.postForEntity<SubCategoryOutDto>(
                BASE_PATH,
                HttpEntity("""{"name": "Market", "categoryId": 1}""", headers)
            )
        Assertions.assertThat(entityOne.statusCode).isEqualTo(HttpStatus.CREATED)
        Assertions.assertThat(entityOne.body).isEqualTo(SubCategoryOutDto(1, "Market", 1))

        val entityTwo =
            restTemplate.postForEntity<SubCategoryOutDto>(
                BASE_PATH,
                HttpEntity("""{"name": "Light", "categoryId": 1}""", headers)
            )
        Assertions.assertThat(entityTwo.statusCode).isEqualTo(HttpStatus.CREATED)
        Assertions.assertThat(entityTwo.body).isEqualTo(SubCategoryOutDto(2, "Light", 1))
    }

    @Test
    @Order(4)
    fun `Get all elements and receive the previous two inserted elements`() {
        val entity = restTemplate.getForEntity<Array<SubCategoryOutDto>>(BASE_PATH)
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).isEqualTo(
            arrayOf(
                SubCategoryOutDto(1, "Market", 1),
                SubCategoryOutDto(2, "Light", 1)
            )
        )
    }

    @Test
    @Order(4)
    fun `Get one element`() {
        val entity = restTemplate.getForEntity<SubCategoryOutDto>("$BASE_PATH/1")
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).isEqualTo(SubCategoryOutDto(1, "Market", 1))
    }

    @Test
    @Order(5)
    fun `Update one element`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity = restTemplate.exchange<SubCategoryOutDto>(
            "$BASE_PATH/1",
            HttpMethod.PUT,
            HttpEntity("""{"name": "Saves"}""", headers)
        )
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).isEqualTo(SubCategoryOutDto(1, "Saves", 1))
    }

    @Test
    @Order(6)
    fun `Delete one element`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity = restTemplate.exchange<String>(
            "$BASE_PATH/1",
            HttpMethod.DELETE,
            HttpEntity(null, headers)
        )
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)

        val entityNotFound = restTemplate.getForEntity<String>("$BASE_PATH/1")
        Assertions.assertThat(entityNotFound.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }
}
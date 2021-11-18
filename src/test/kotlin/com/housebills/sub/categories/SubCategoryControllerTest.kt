package com.housebills.sub.categories

import com.housebills.BaseIntegrationTest
import com.housebills.categories.dtos.CategoryOutDto
import com.housebills.sub.categories.dtos.SubCategoryOutDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
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

internal class SubCategoryControllerTest(@Autowired val restTemplate: TestRestTemplate) : BaseIntegrationTest() {
    val BASE_PATH = "/sub-categories"

    private fun createCategory(): Unit {
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
        Assertions.assertThat(entityOne.body).isEqualTo(SubCategoryOutDto(1, "Market"))

        val entityTwo =
            restTemplate.postForEntity<SubCategoryOutDto>(
                BASE_PATH,
                HttpEntity("""{"name": "Light", "categoryId": 1}""", headers)
            )
        Assertions.assertThat(entityTwo.statusCode).isEqualTo(HttpStatus.CREATED)
        Assertions.assertThat(entityTwo.body).isEqualTo(SubCategoryOutDto(2, "Light"))
    }

    @Test
    @Order(4)
    fun `Get all elements and receive the previous two inserted elements`() {
        val entity = restTemplate.getForEntity<String>(BASE_PATH)
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        // TODO: Add a converter from SubCategoryOutDto to JSON here
        Assertions.assertThat(entity.body).isEqualTo("""[{"id":1,"name":"Market"},{"id":2,"name":"Light"}]""")
    }

    @Test
    @Order(4)
    fun `Get one element`() {
        val entity = restTemplate.getForEntity<SubCategoryOutDto>("$BASE_PATH/1")
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).isEqualTo(SubCategoryOutDto(1, "Market"))
    }
}
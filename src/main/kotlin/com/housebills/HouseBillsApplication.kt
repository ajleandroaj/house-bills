package com.housebills

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class HouseBillsApplication

fun main(args: Array<String>) {
    runApplication<HouseBillsApplication>(*args)
}

@RestController
class DemoResource(val service: DemoService) {

    @GetMapping
    fun index(): List<Demo> = service.findDemos()

    @PostMapping
    fun post(@RequestBody demo: Demo): Demo {
        service.post(demo)
        return demo
    }
}

@Service
class DemoService(val db: DemoRepository) {

    fun findDemos(): List<Demo> = db.findDemos()

    fun post(demo: Demo) {
        db.save(demo)
    }
}

interface DemoRepository : CrudRepository<Demo, Int> {

    @Query("select * from demos")
    fun findDemos(): List<Demo>
}

@Table("DEMOS")
data class Demo(
    @Id val id: Int?,
    val text: String
)
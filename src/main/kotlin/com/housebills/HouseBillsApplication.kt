package com.housebills

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HouseBillsApplication

fun main(args: Array<String>) {
    runApplication<HouseBillsApplication>(*args)
}
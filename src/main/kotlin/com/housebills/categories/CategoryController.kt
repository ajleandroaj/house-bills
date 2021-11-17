package com.housebills.categories

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val categoryCRUDService: CategoryCRUDService) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun post(@RequestBody category: Category): Category = categoryCRUDService.post(category)

    @GetMapping
    fun getAll(): List<Category> = categoryCRUDService.findAll()

    @GetMapping("/{categoryId}")
    fun getOne(@PathVariable("categoryId") categoryId: Long): Category = categoryCRUDService.findOne(categoryId)
}
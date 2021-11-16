package com.housebills.categories

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val categoryService: CategoryService) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun post(@RequestBody category: CategoryModel): CategoryModel = categoryService.post(category)

    @GetMapping
    fun getAll(): List<CategoryModel> = categoryService.findAll()

    @GetMapping("/{categoryId}")
    fun getOne(@PathVariable("categoryId") categoryId: Int): CategoryModel = categoryService.findOne(categoryId)
}
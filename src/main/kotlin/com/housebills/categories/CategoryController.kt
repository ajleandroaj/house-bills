package com.housebills.categories

import com.housebills.categories.dtos.CategoryOutDto
import com.housebills.categories.dtos.CreateCategoryInDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val categoryCRUDService: CategoryCRUDService) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun post(@RequestBody category: CreateCategoryInDto): CategoryOutDto = categoryCRUDService.post(category)

    @GetMapping
    fun getAll(): List<CategoryOutDto> = categoryCRUDService.findAll()

    @GetMapping("/{categoryId}")
    fun getOne(@PathVariable("categoryId") categoryId: Long): CategoryOutDto = categoryCRUDService.findOne(categoryId)
}
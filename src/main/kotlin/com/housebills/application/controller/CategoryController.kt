package com.housebills.application.controller

import com.housebills.application.dto.category.CategoryOutDto
import com.housebills.application.dto.category.CreateCategoryInDto
import com.housebills.application.dto.category.UpdateCategoryInDto
import com.housebills.application.facade.CategoryFacade
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val categoryFacade: CategoryFacade) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createOne(@RequestBody createCategoryInDto: CreateCategoryInDto): CategoryOutDto =
        categoryFacade.createOne(createCategoryInDto)

    @GetMapping
    fun readAll(): List<CategoryOutDto> = categoryFacade.readAll()

    @GetMapping("/{categoryId}")
    fun readOne(@PathVariable("categoryId") categoryId: Long): CategoryOutDto = categoryFacade.readOne(categoryId)

    @PutMapping("/{categoryId}")
    fun updateOne(
        @PathVariable("categoryId") categoryId: Long,
        @RequestBody updateCategoryInDto: UpdateCategoryInDto
    ): CategoryOutDto = categoryFacade.updateOne(categoryId, updateCategoryInDto)

    @DeleteMapping("/{categoryId}")
    fun deleteOne(@PathVariable("categoryId") categoryId: Long): Unit = categoryFacade.deleteOne(categoryId)
}
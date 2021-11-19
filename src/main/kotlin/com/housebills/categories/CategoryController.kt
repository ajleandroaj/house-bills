package com.housebills.categories

import com.housebills.categories.dtos.CategoryOutDto
import com.housebills.categories.dtos.CreateCategoryInDto
import com.housebills.categories.dtos.UpdateCategoryInDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val categoryCRUDService: CategoryCRUDService) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createOne(@RequestBody createCategoryInDto: CreateCategoryInDto): CategoryOutDto =
        categoryCRUDService.createOne(createCategoryInDto)

    @GetMapping
    fun readAll(): List<CategoryOutDto> = categoryCRUDService.readAll()

    @GetMapping("/{categoryId}")
    fun readOne(@PathVariable("categoryId") categoryId: Long): CategoryOutDto = categoryCRUDService.readOne(categoryId)

    @PutMapping("/{categoryId}")
    fun updateOne(
        @PathVariable("categoryId") categoryId: Long,
        @RequestBody updateCategoryInDto: UpdateCategoryInDto
    ): CategoryOutDto = categoryCRUDService.update(categoryId, updateCategoryInDto)

    @DeleteMapping("/{categoryId}")
    fun deleteOne(@PathVariable("categoryId") categoryId: Long): Unit = categoryCRUDService.deleteOne(categoryId)
}
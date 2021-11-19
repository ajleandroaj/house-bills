package com.housebills.sub.categories

import com.housebills.sub.categories.dtos.CreateSubCategoryInDto
import com.housebills.sub.categories.dtos.SubCategoryOutDto
import com.housebills.sub.categories.dtos.UpdateSubCategoryInDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sub-categories")
class SubCategoryController(val subCategoryCRUDService: SubCategoryCRUDService) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createOne(@RequestBody subCategory: CreateSubCategoryInDto): SubCategoryOutDto =
        subCategoryCRUDService.createOne(subCategory)

    @GetMapping
    fun readAll(): List<SubCategoryOutDto> = subCategoryCRUDService.readAll()

    @GetMapping("/{subCategoryId}")
    fun readOne(@PathVariable("subCategoryId") subCategoryId: Long): SubCategoryOutDto =
        subCategoryCRUDService.readOne(subCategoryId)

    @PutMapping("/{subCategoryId}")
    fun updateOne(
        @PathVariable("subCategoryId") subCategoryId: Long,
        @RequestBody updateSubCategoryInDto: UpdateSubCategoryInDto
    ): SubCategoryOutDto = subCategoryCRUDService.updateOne(subCategoryId, updateSubCategoryInDto)

    @DeleteMapping("/{subCategoryId}")
    fun deleteOne(@PathVariable("subCategoryId") subCategoryId: Long): Unit =
        subCategoryCRUDService.deleteOne(subCategoryId)

}
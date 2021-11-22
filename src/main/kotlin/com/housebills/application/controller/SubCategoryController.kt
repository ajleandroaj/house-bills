package com.housebills.application.controller

import com.housebills.application.dto.sub.category.CreateSubCategoryInDto
import com.housebills.application.dto.sub.category.SubCategoryOutDto
import com.housebills.application.dto.sub.category.UpdateSubCategoryInDto
import com.housebills.application.facade.SubCategoryFacade
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sub-categories")
class SubCategoryController(val subCategoryFacade: SubCategoryFacade) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun createOne(@RequestBody subCategory: CreateSubCategoryInDto): SubCategoryOutDto =
        subCategoryFacade.createOne(subCategory)

    @GetMapping
    fun readAll(): List<SubCategoryOutDto> = subCategoryFacade.readAll()

    @GetMapping("/{subCategoryId}")
    fun readOne(@PathVariable("subCategoryId") subCategoryId: Long): SubCategoryOutDto =
        subCategoryFacade.readOne(subCategoryId)

    @PutMapping("/{subCategoryId}")
    fun updateOne(
        @PathVariable("subCategoryId") subCategoryId: Long,
        @RequestBody updateSubCategoryInDto: UpdateSubCategoryInDto
    ): SubCategoryOutDto = subCategoryFacade.updateOne(subCategoryId, updateSubCategoryInDto)

    @DeleteMapping("/{subCategoryId}")
    fun deleteOne(@PathVariable("subCategoryId") subCategoryId: Long): Unit =
        subCategoryFacade.deleteOne(subCategoryId)

}
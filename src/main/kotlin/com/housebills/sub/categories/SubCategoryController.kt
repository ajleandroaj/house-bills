package com.housebills.sub.categories

import com.housebills.sub.categories.dtos.CreateSubCategoryInDto
import com.housebills.sub.categories.dtos.SubCategoryOutDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sub-categories")
class SubCategoryController(val subCategoryCRUDService: SubCategoryCRUDService) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun post(@RequestBody subCategory: CreateSubCategoryInDto): SubCategoryOutDto =
        subCategoryCRUDService.post(subCategory)

    @GetMapping
    fun getAll(): List<SubCategoryOutDto> = subCategoryCRUDService.findAll()

    @GetMapping("/{subCategoryId}")
    fun getOne(@PathVariable("subCategoryId") subCategoryId: Long): SubCategoryOutDto =
        subCategoryCRUDService.findOne(subCategoryId)
}
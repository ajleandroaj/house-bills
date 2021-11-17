package com.housebills.sub.categories

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sub-categories")
class SubCategoryController(val subCategoryCRUDService: SubCategoryCRUDService) {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun post(@RequestBody subCategory: SubCategory): SubCategory = subCategoryCRUDService.post(subCategory)

    @GetMapping
    fun getAll(): List<SubCategory> = subCategoryCRUDService.findAll()

    @GetMapping("/{subCategoryId}")
    fun getOne(@PathVariable("subCategoryId") subCategoryId: Long): SubCategory =
        subCategoryCRUDService.findOne(subCategoryId)
}
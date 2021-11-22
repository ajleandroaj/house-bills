package com.housebills.application.facade

import com.housebills.application.dto.category.CategoryOutDto
import com.housebills.application.dto.category.CreateCategoryInDto
import com.housebills.application.dto.category.UpdateCategoryInDto
import com.housebills.domain.exception.category.CategoryNotFoundException
import com.housebills.domain.entity.Category
import com.housebills.domain.irepository.query.CategoryQueryRepository
import com.housebills.domain.service.category.CategoryCRUDService
import org.springframework.stereotype.Service

@Service
class CategoryFacade(
    val categoryQueryRepository: CategoryQueryRepository,
    val categoryCRUDService: CategoryCRUDService,
) {

    fun createOne(categoryInDto: CreateCategoryInDto): CategoryOutDto {
        val category = categoryCRUDService.createOne(Category(categoryInDto.name))
        return CategoryOutDto(category.id, category.name)
    }

    fun readAll(): List<CategoryOutDto> = categoryQueryRepository.findAll()
        .toList()
        .map { CategoryOutDto(it.id, it.name) }

    fun readOne(categoryId: Long): CategoryOutDto {
        val category = categoryQueryRepository
            .findById(categoryId)
            .orElseThrow { CategoryNotFoundException() }

        return CategoryOutDto(category.id, category.name)
    }

    fun updateOne(categoryId: Long, updateCategoryInDto: UpdateCategoryInDto): CategoryOutDto {
        val category = categoryCRUDService.updateName(categoryId, updateCategoryInDto.name)
        return CategoryOutDto(category.id, category.name)
    }

    fun deleteOne(categoryId: Long) {
        val category = categoryQueryRepository
            .findById(categoryId)
            .orElseThrow { CategoryNotFoundException() }

        categoryCRUDService.deleteOne(category)
    }
}
package com.housebills.categories

import com.housebills.categories.dtos.CategoryOutDto
import com.housebills.categories.dtos.CreateCategoryInDto
import com.housebills.categories.dtos.UpdateCategoryInDto
import com.housebills.categories.exceptions.CategoryNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CategoryCRUDService(val categoryRepository: CategoryRepository) {

    @Transactional
    fun createOne(categoryInDto: CreateCategoryInDto): CategoryOutDto {
        val category = categoryRepository.save(Category(categoryInDto.name))
        return CategoryOutDto(category.id, category.name)
    }

    fun readAll(): List<CategoryOutDto> = categoryRepository.findAll()
        .toList()
        .map { CategoryOutDto(it.id, it.name) }

    fun readOne(categoryId: Long): CategoryOutDto {
        val category = categoryRepository
            .findById(categoryId)
            .orElseThrow { CategoryNotFoundException() }

        return CategoryOutDto(category.id, category.name)
    }

    fun update(categoryId: Long, updateCategoryInDto: UpdateCategoryInDto): CategoryOutDto {
        var category = categoryRepository
            .findById(categoryId)
            .orElseThrow { CategoryNotFoundException() }

        category.name = updateCategoryInDto.name
        category = categoryRepository.save(category)

        return CategoryOutDto(category.id, category.name)
    }

    fun deleteOne(categoryId: Long) {
        val category = categoryRepository
            .findById(categoryId)
            .orElseThrow { CategoryNotFoundException() }

        categoryRepository.delete(category)
    }
}
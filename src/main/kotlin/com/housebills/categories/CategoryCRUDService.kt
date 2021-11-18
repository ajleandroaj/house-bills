package com.housebills.categories

import com.housebills.categories.dtos.CategoryOutDto
import com.housebills.categories.dtos.CreateCategoryInDto
import com.housebills.categories.exceptions.CategoryNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CategoryCRUDService(val categoryRepository: CategoryRepository) {

    @Transactional
    fun post(categoryInDto: CreateCategoryInDto): CategoryOutDto {
        val category = categoryRepository.save(Category(categoryInDto.name))
        return CategoryOutDto(category.id, category.name)
    }

    fun findAll(): List<CategoryOutDto> = categoryRepository.findAll()
        .toList()
        .map { CategoryOutDto(it.id, it.name) }

    fun findOne(categoryId: Long): CategoryOutDto {
        val category = categoryRepository
            .findById(categoryId)
            .orElseThrow { CategoryNotFoundException() }

        return CategoryOutDto(category.id, category.name)
    }
}
package com.housebills.domain.service.category

import com.housebills.domain.exception.category.CategoryNotFoundException
import com.housebills.domain.irepository.command.CategoryCommandRepository
import com.housebills.domain.entity.Category
import org.springframework.stereotype.Service

@Service
class CategoryCRUDService(val categoryRepository: CategoryCommandRepository) {

    fun createOne(category: Category): Category {
        return categoryRepository.save(category)
    }

    fun updateName(categoryId: Long, name: String): Category {
        val category = categoryRepository
            .findById(categoryId)
            .orElseThrow { CategoryNotFoundException() }

        category.name = name
        return categoryRepository.save(category)
    }

    fun deleteOne(category: Category) {
        categoryRepository.delete(category)
    }
}
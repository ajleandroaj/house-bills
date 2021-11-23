package com.housebills.domain.service.category

import com.housebills.domain.command.category.CreateCategoryCommand
import com.housebills.domain.command.category.DeleteCategoryCommand
import com.housebills.domain.command.category.UpdateCategoryCommand
import com.housebills.domain.exception.category.CategoryNotFoundException
import com.housebills.domain.irepository.command.CategoryCommandRepository
import com.housebills.domain.entity.Category
import org.springframework.stereotype.Service

@Service
class CategoryCRUDService(val categoryRepository: CategoryCommandRepository) {

    fun createOne(createCategoryCommand: CreateCategoryCommand): Category {
        val category = Category(createCategoryCommand.name)
        return categoryRepository.save(category)
    }

    fun updateOne(updateCategoryCommand: UpdateCategoryCommand): Category {
        val category = categoryRepository
            .findById(updateCategoryCommand.id)
            .orElseThrow { CategoryNotFoundException() }

        category.name = updateCategoryCommand.name
        return categoryRepository.save(category)
    }

    fun deleteOne(deleteCategoryCommand: DeleteCategoryCommand) {
        val category = categoryRepository
            .findById(deleteCategoryCommand.id)
            .orElseThrow { CategoryNotFoundException() }

        categoryRepository.delete(category)
    }
}
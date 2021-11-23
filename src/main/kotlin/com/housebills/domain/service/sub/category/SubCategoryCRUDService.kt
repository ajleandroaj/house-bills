package com.housebills.domain.service.sub.category

import com.housebills.domain.command.sub.category.CreateSubCategoryCommand
import com.housebills.domain.command.sub.category.DeleteSubCategoryCommand
import com.housebills.domain.command.sub.category.UpdateSubCategoryCommand
import com.housebills.domain.entity.SubCategory
import com.housebills.domain.exception.category.CategoryNotFoundException
import com.housebills.domain.exception.sub.category.SubCategoryNotFoundException
import com.housebills.domain.irepository.command.CategoryCommandRepository
import com.housebills.domain.irepository.command.SubCategoryCommandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubCategoryCRUDService(
    val subCategoryCommandRepository: SubCategoryCommandRepository,
    val categoryCommandRepository: CategoryCommandRepository,
) {

    @Transactional
    fun createOne(createSubCategoryCommand: CreateSubCategoryCommand): SubCategory {
        val category =
            categoryCommandRepository.findById(createSubCategoryCommand.categoryId)
                .orElseThrow { CategoryNotFoundException() }

        val subCategory = SubCategory(createSubCategoryCommand.name, category)
        return subCategoryCommandRepository.save(subCategory)
    }

    @Transactional
    fun updateOne(updateSubCategoryCommand: UpdateSubCategoryCommand): SubCategory {
        val subCategory = subCategoryCommandRepository
            .findById(updateSubCategoryCommand.id)
            .orElseThrow { SubCategoryNotFoundException() }

        val category =
            categoryCommandRepository.findById(updateSubCategoryCommand.categoryId)
                .orElseThrow { CategoryNotFoundException() }

        subCategory.name = updateSubCategoryCommand.name
        subCategory.category = category
        return subCategoryCommandRepository.save(subCategory)
    }

    @Transactional
    fun deleteOne(deleteSubCategoryCommand: DeleteSubCategoryCommand) {
        val subCategory = subCategoryCommandRepository
            .findById(deleteSubCategoryCommand.id)
            .orElseThrow { SubCategoryNotFoundException() }

        subCategoryCommandRepository.delete(subCategory)
    }
}
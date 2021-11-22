package com.housebills.domain.service.sub.category

import com.housebills.domain.entity.SubCategory
import com.housebills.domain.irepository.command.SubCategoryCommandRepository
import org.springframework.stereotype.Service

@Service
class SubCategoryCRUDService(
    val subCategoryRepository: SubCategoryCommandRepository,
) {

    fun createOne(subCategory: SubCategory): SubCategory {
        return subCategoryRepository.save(subCategory)
    }

    fun updateName(subCategory: SubCategory, name: String): SubCategory {
        subCategory.name = name
        return subCategoryRepository.save(subCategory)
    }

    fun deleteOne(subCategory: SubCategory) {
        subCategoryRepository.delete(subCategory)
    }
}
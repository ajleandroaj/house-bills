package com.housebills.sub.categories

import com.housebills.categories.CategoryRepository
import com.housebills.categories.exceptions.CategoryNotFoundException
import com.housebills.sub.categories.dtos.CreateSubCategoryInDto
import com.housebills.sub.categories.dtos.SubCategoryOutDto
import com.housebills.sub.categories.exceptions.SubCategoryNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SubCategoryCRUDService(
    val subCategoryRepository: SubCategoryRepository,
    val categoryRepository: CategoryRepository
) {

    @Transactional
    fun post(createSubCategoryInDto: CreateSubCategoryInDto): SubCategoryOutDto {
        val category =
            categoryRepository.findById(createSubCategoryInDto.categoryId)
                .orElseThrow { CategoryNotFoundException() }

        var subCategory = SubCategory(createSubCategoryInDto.name, category)
        subCategory = subCategoryRepository.save(subCategory)

        return SubCategoryOutDto(subCategory.id, subCategory.name)
    }

    fun findAll(): List<SubCategoryOutDto> =
        subCategoryRepository.findAll()
            .toList()
            .map { SubCategoryOutDto(it.id, it.name) }

    fun findOne(subCategoryId: Long): SubCategoryOutDto {
        val subCategory = subCategoryRepository
            .findById(subCategoryId)
            .orElseThrow { SubCategoryNotFoundException() }

        return SubCategoryOutDto(subCategory.id, subCategory.name)
    }
}
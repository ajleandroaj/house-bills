package com.housebills.sub.categories

import com.housebills.categories.CategoryRepository
import com.housebills.categories.exceptions.CategoryNotFoundException
import com.housebills.sub.categories.dtos.CreateSubCategoryInDto
import com.housebills.sub.categories.dtos.SubCategoryOutDto
import com.housebills.sub.categories.dtos.UpdateSubCategoryInDto
import com.housebills.sub.categories.exceptions.SubCategoryNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SubCategoryCRUDService(
    val subCategoryRepository: SubCategoryRepository,
    val categoryRepository: CategoryRepository
) {

    @Transactional
    fun createOne(createSubCategoryInDto: CreateSubCategoryInDto): SubCategoryOutDto {
        val category =
            categoryRepository.findById(createSubCategoryInDto.categoryId)
                .orElseThrow { CategoryNotFoundException() }

        var subCategory = SubCategory(createSubCategoryInDto.name, category)
        subCategory = subCategoryRepository.save(subCategory)

        return SubCategoryOutDto(subCategory.id, subCategory.name)
    }

    fun readAll(): List<SubCategoryOutDto> =
        subCategoryRepository.findAll()
            .toList()
            .map { SubCategoryOutDto(it.id, it.name) }

    fun readOne(subCategoryId: Long): SubCategoryOutDto {
        val subCategory = subCategoryRepository
            .findById(subCategoryId)
            .orElseThrow { SubCategoryNotFoundException() }

        return SubCategoryOutDto(subCategory.id, subCategory.name)
    }

    fun updateOne(subCategoryId: Long, updateSubCategoryInDto: UpdateSubCategoryInDto): SubCategoryOutDto {
        var subCategory = subCategoryRepository
            .findById(subCategoryId)
            .orElseThrow { SubCategoryNotFoundException() }

        subCategory.name = updateSubCategoryInDto.name
        subCategory = subCategoryRepository.save(subCategory)

        return SubCategoryOutDto(subCategory.id, subCategory.name)
    }

    fun deleteOne(subCategoryId: Long) {
        val subCategory = subCategoryRepository
            .findById(subCategoryId)
            .orElseThrow { CategoryNotFoundException() }

        subCategoryRepository.delete(subCategory)
    }
}
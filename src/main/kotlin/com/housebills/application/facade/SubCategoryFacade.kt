package com.housebills.application.facade

import com.housebills.domain.exception.category.CategoryNotFoundException
import com.housebills.application.dto.sub.category.CreateSubCategoryInDto
import com.housebills.application.dto.sub.category.SubCategoryOutDto
import com.housebills.application.dto.sub.category.UpdateSubCategoryInDto
import com.housebills.domain.exception.sub.category.SubCategoryNotFoundException
import com.housebills.domain.entity.SubCategory
import com.housebills.domain.irepository.query.CategoryQueryRepository
import com.housebills.domain.irepository.query.SubCategoryQueryRepository
import com.housebills.domain.service.sub.category.SubCategoryCRUDService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SubCategoryFacade(
    val subCategoryQueryRepository: SubCategoryQueryRepository,
    val categoryQueryRepository: CategoryQueryRepository,
    val subCategoryCRUDService: SubCategoryCRUDService
) {

    @Transactional
    fun createOne(createSubCategoryInDto: CreateSubCategoryInDto): SubCategoryOutDto {
        val category =
            categoryQueryRepository.findById(createSubCategoryInDto.categoryId)
                .orElseThrow { CategoryNotFoundException() }

        var subCategory = SubCategory(createSubCategoryInDto.name, category)
        subCategory = subCategoryCRUDService.createOne(subCategory)

        return SubCategoryOutDto(subCategory.id, subCategory.name, subCategory.category.id)
    }

    fun readAll(): List<SubCategoryOutDto> =
        subCategoryQueryRepository.findAll()
            .toList()
            .map { SubCategoryOutDto(it.id, it.name, it.category.id) }

    fun readOne(subCategoryId: Long): SubCategoryOutDto {
        val subCategory = subCategoryQueryRepository
            .findById(subCategoryId)
            .orElseThrow { SubCategoryNotFoundException() }

        return SubCategoryOutDto(subCategory.id, subCategory.name, subCategory.category.id)
    }

    fun updateOne(subCategoryId: Long, updateSubCategoryInDto: UpdateSubCategoryInDto): SubCategoryOutDto {
        var subCategory = subCategoryQueryRepository
            .findById(subCategoryId)
            .orElseThrow { SubCategoryNotFoundException() }

        subCategory = subCategoryCRUDService.updateName(subCategory, updateSubCategoryInDto.name)

        return SubCategoryOutDto(subCategory.id, subCategory.name, subCategory.category.id)
    }

    fun deleteOne(subCategoryId: Long) {
        val subCategory = subCategoryQueryRepository
            .findById(subCategoryId)
            .orElseThrow { CategoryNotFoundException() }

        subCategoryCRUDService.deleteOne(subCategory)
    }
}
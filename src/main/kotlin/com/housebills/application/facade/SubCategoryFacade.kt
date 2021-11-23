package com.housebills.application.facade

import com.housebills.application.dto.sub.category.CreateSubCategoryInDto
import com.housebills.application.dto.sub.category.SubCategoryOutDto
import com.housebills.application.dto.sub.category.UpdateSubCategoryInDto
import com.housebills.domain.command.sub.category.CreateSubCategoryCommand
import com.housebills.domain.command.sub.category.DeleteSubCategoryCommand
import com.housebills.domain.command.sub.category.UpdateSubCategoryCommand
import com.housebills.domain.exception.sub.category.SubCategoryNotFoundException
import com.housebills.domain.irepository.query.SubCategoryQueryRepository
import com.housebills.domain.service.sub.category.SubCategoryCRUDService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SubCategoryFacade(
    val subCategoryQueryRepository: SubCategoryQueryRepository,
    val subCategoryCRUDService: SubCategoryCRUDService
) {

    @Transactional
    fun createOne(createSubCategoryInDto: CreateSubCategoryInDto): SubCategoryOutDto {
        val subCategory = subCategoryCRUDService.createOne(
            CreateSubCategoryCommand(
                createSubCategoryInDto.name,
                createSubCategoryInDto.categoryId
            )
        )

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
        val subCategory =
            subCategoryCRUDService.updateOne(
                UpdateSubCategoryCommand(
                    subCategoryId,
                    updateSubCategoryInDto.name,
                    updateSubCategoryInDto.categoryId
                )
            )

        return SubCategoryOutDto(subCategory.id, subCategory.name, subCategory.category.id)
    }

    fun deleteOne(subCategoryId: Long) {
        subCategoryCRUDService.deleteOne(DeleteSubCategoryCommand(subCategoryId))
    }
}
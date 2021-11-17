package com.housebills.sub.categories

import com.housebills.sub.categories.exceptions.SubCategoryNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SubCategoryCRUDService(val subCategoryRepository: SubCategoryRepository) {

    @Transactional
    fun post(subCategory: SubCategory): SubCategory = subCategoryRepository.save(subCategory)

    fun findAll(): List<SubCategory> = subCategoryRepository.findAll().toList()

    fun findOne(subCategoryId: Long): SubCategory = subCategoryRepository
        .findById(subCategoryId)
        .orElseThrow { SubCategoryNotFoundException() }
}
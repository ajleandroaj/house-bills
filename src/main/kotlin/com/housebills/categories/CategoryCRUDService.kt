package com.housebills.categories

import com.housebills.categories.exceptions.CategoryNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CategoryCRUDService(val categoryRepository: CategoryRepository) {

    @Transactional
    fun post(category: Category): Category = categoryRepository.save(category)

    fun findAll(): List<Category> = categoryRepository.findAll().toList()

    fun findOne(categoryId: Long): Category = categoryRepository
        .findById(categoryId)
        .orElseThrow { CategoryNotFoundException() }
}
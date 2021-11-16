package com.housebills.categories

import com.housebills.categories.exceptions.CategoryNotFoundException
import org.springframework.stereotype.Service

@Service
class CategoryService(val db: CategoryRepository) {

    fun post(category: CategoryModel): CategoryModel = db.save(category)

    fun findAll(): List<CategoryModel> = db.findAll().toList()

    fun findOne(categoryId: Int): CategoryModel = db
        .findById(categoryId)
        .orElseThrow { CategoryNotFoundException() }
}
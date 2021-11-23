package com.housebills.domain.command.sub.category

data class CreateSubCategoryCommand(
    val name: String,
    val categoryId: Long,
)

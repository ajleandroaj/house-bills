package com.housebills.domain.command.sub.category

data class UpdateSubCategoryCommand(
    val id: Long,
    val name: String,
    val categoryId: Long,
)

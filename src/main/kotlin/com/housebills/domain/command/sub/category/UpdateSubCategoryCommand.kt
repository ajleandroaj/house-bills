package com.housebills.domain.command.sub.category

data class UpdateSubCategoryCommand(
    val id: Long,
    val name: String,
    // TODO: Add this
    // val categoryId: Long,
)

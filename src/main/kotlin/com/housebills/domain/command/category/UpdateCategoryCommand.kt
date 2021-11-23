package com.housebills.domain.command.category

data class UpdateCategoryCommand(
    val id: Long,
    val name: String,
)

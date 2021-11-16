package com.housebills.categories

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CATEGORIES")
data class CategoryModel(
    @Id val id: Int?,
    val name: String
)

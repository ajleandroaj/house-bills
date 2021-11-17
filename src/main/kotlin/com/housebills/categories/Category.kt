package com.housebills.categories

import com.housebills.sub.categories.SubCategory
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "categories")
class Category(
    @Column(unique = true)
    val name: String = ""
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @OneToMany(mappedBy = "category", cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    private val _subCategories = mutableListOf<SubCategory>()

    val subCategories get() = _subCategories
}
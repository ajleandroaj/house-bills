package com.housebills.categories

import com.fasterxml.jackson.annotation.JsonManagedReference
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
    var name: String = ""
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    val subCategories = mutableListOf<SubCategory>()
}

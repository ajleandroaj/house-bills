package com.housebills.sub.categories

import com.fasterxml.jackson.annotation.JsonBackReference
import com.housebills.categories.Category
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "sub_categories")
class SubCategory(
    @Column(unique = true)
    val name: String = "",

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], optional = false)
    @JoinColumn(name = "category_id")
    val category: Category? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}

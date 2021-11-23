package com.housebills.domain.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Column
import javax.persistence.Entity
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
    var name: String = "",

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    var category: Category = Category()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}

package com.housebills.sub.categories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubCategoryRepository : CrudRepository<SubCategory, Long>
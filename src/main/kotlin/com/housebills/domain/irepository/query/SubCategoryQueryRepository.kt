package com.housebills.domain.irepository.query

import com.housebills.domain.entity.SubCategory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubCategoryQueryRepository : CrudRepository<SubCategory, Long>
package com.housebills.domain.irepository.query

import com.housebills.domain.entity.Category
import org.springframework.stereotype.Repository

@Repository
interface CategoryQueryRepository : QueryRepository<Category, Long>
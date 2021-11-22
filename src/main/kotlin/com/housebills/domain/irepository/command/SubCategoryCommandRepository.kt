package com.housebills.domain.irepository.command

import com.housebills.domain.entity.SubCategory
import org.springframework.stereotype.Repository

@Repository
interface SubCategoryCommandRepository : CommandRepository<SubCategory, Long>
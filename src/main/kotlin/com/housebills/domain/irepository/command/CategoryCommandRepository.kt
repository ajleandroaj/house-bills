package com.housebills.domain.irepository.command

import com.housebills.domain.entity.Category
import org.springframework.stereotype.Repository

@Repository
interface CategoryCommandRepository : CommandRepository<Category, Long>
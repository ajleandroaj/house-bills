package com.housebills.domain.irepository.command

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface CommandRepository<T, ID> : CrudRepository<T, ID>
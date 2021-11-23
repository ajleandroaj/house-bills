package com.housebills.domain.exception.sub.category

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class SubCategoryNotFoundException : RuntimeException()
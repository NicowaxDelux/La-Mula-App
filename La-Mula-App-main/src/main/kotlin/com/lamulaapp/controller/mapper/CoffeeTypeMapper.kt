package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.CoffeeTypeDto
import com.lamulaapp.domain.CoffeeType

fun CoffeeType.toDto() = CoffeeTypeDto(
    idCoffeeType = this.idCoffeeType,
    name = this.name,
    description = this.description,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun CoffeeTypeDto.toEntity() = CoffeeType(
    idCoffeeType = this.idCoffeeType,
    name = this.name!!,
    description = this.description!!,
    createdAt = this.createdAt!!,
    createdBy = this.createdBy!!,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
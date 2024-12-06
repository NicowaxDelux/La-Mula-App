package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.CoffeeTypeDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

fun validateCreateCoffeeType(coffeeTypeDto: CoffeeTypeDto): ValidationResult<CoffeeTypeDto> {
    val validateCoffeeType = Validation {
        CoffeeTypeDto::name required {}
        CoffeeTypeDto::description required {}
        CoffeeTypeDto::createdAt required {}
        CoffeeTypeDto::createdBy required {}
    }
    return validateCoffeeType(coffeeTypeDto)
}

fun validateUpdateCoffeeType(coffeeTypeDto: CoffeeTypeDto): ValidationResult<CoffeeTypeDto> {
    val validateCoffeeType = Validation {
        CoffeeTypeDto::idCoffeeType required {}
        CoffeeTypeDto::name required {}
        CoffeeTypeDto::description required {}
        CoffeeTypeDto::createdAt required {}
        CoffeeTypeDto::createdBy required {}
        CoffeeTypeDto::updatedAt required {}
        CoffeeTypeDto::updatedBy required {}
    }
    return validateCoffeeType(coffeeTypeDto)
}
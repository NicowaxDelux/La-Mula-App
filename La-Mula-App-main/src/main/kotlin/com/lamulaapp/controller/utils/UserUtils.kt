package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.UserDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

fun validateCreateUser(userDto: UserDto): ValidationResult<UserDto> {
    val validateUser = Validation {
        UserDto::loginDto required {}
        UserDto::name required {}
        UserDto::email required {}
        UserDto::createdAt required {}
        UserDto::createdBy required {}
    }
    return validateUser(userDto)
}

fun validateUpdateUser(userDto: UserDto): ValidationResult<UserDto> {
    val validationUser = Validation {
        UserDto::idUser required {}
        UserDto::name required {}
        UserDto::email required {}
        UserDto::address required {}
        UserDto::phone required {}
        UserDto::createdAt required {}
        UserDto::createdBy required {}
        UserDto::updatedBy required {}
    }
    return validationUser(userDto)
}
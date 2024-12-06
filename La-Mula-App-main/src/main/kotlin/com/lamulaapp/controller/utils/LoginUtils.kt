package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.LoginDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength

fun validateCreateLogin(loginDto: LoginDto): ValidationResult<LoginDto> {
    val validateLogin = Validation {
        LoginDto::role required {}
        LoginDto::username required {
            minLength(3)
        }
        LoginDto::password required {
            minLength(6)
            maxLength(20)
        }
        LoginDto::role required {}
        LoginDto::createdAt required {}
        LoginDto::createdBy required {}
    }

    return validateLogin(loginDto)
}

fun validateUpdatePassword(loginDto: LoginDto): ValidationResult<LoginDto> {
    val validateLogin = Validation {
        LoginDto::idLogin required {}
        LoginDto::password required {}
        LoginDto::updatedAt required {}
        LoginDto::updatedBy required {}
    }

    return validateLogin(loginDto)
}

fun validateLogin(loginDto: LoginDto): ValidationResult<LoginDto> {
    val validateLogin = Validation {
        LoginDto::username required {
            minLength(3)
        }
        LoginDto::password required {
            minLength(6)
            maxLength(20)
        }
    }
    return validateLogin(loginDto)
}
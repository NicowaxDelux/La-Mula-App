package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.SignUpRequestDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength

fun validateSignUp(signUpRequestDto: SignUpRequestDto): ValidationResult<SignUpRequestDto> {
    val validateSignUp = Validation {
        SignUpRequestDto::name required {}
        SignUpRequestDto::email required {}
        SignUpRequestDto::password required {
            minLength(6)
            maxLength(20)
        }
        SignUpRequestDto::roleName required {}
    }
    return validateSignUp(signUpRequestDto)
}
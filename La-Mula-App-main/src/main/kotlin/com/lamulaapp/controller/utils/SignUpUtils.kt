package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.SignUpRequestDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

fun validateSignUp(signUpRequestDto: SignUpRequestDto): ValidationResult<SignUpRequestDto> {
    val validateSignUp = Validation {
        SignUpRequestDto::name required {}
        SignUpRequestDto::email required {}
        SignUpRequestDto::password required {}
        SignUpRequestDto::roleName required {}
    }
    return validateSignUp(signUpRequestDto)
}
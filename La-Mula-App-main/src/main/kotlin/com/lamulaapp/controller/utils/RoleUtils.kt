package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.RoleDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

fun validateCreateRole(roleDto: RoleDto): ValidationResult<RoleDto> {
    val validationRole = Validation {
        RoleDto::name required {}
        RoleDto::description required {}
        RoleDto::createdAt required {}
        RoleDto::createdBy required {}
    }
    return validationRole(roleDto)
}

fun validateUpdateRole(roleDto: RoleDto): ValidationResult<RoleDto> {
    val validationRole = Validation {
        RoleDto::idRole required {}
        RoleDto::name required {}
        RoleDto::description required {}
        RoleDto::createdAt required {}
        RoleDto::createdBy required {}
        RoleDto::updatedBy required {}
    }
    return validationRole(roleDto)
}

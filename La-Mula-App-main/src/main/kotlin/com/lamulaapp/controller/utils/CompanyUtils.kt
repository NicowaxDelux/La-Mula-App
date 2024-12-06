package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.CompanyDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

fun validateCreateCompany(companyDto: CompanyDto): ValidationResult<CompanyDto> {
    val validateCompany = Validation {
        CompanyDto::loginDto required {}
        CompanyDto::name required {}
        CompanyDto::email required {}
        CompanyDto::createdAt required {}
        CompanyDto::createdBy required {}
    }
    return validateCompany(companyDto)
}

fun validateUpdateCompany(companyDto: CompanyDto): ValidationResult<CompanyDto> {
    val validateCompany = Validation {
        CompanyDto::idCompany required {}
        CompanyDto::loginDto required {}
        CompanyDto::name required {}
        CompanyDto::email required {}
        CompanyDto::createdAt required {}
        CompanyDto::createdBy required {}
        CompanyDto::updatedAt required {}
        CompanyDto::updatedBy required {}
    }
    return validateCompany(companyDto)
}
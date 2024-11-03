package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.CompanyDto
import com.lamulaapp.domain.Company

fun Company.toDto() = CompanyDto(
    idCompany = this.idCompany,
    loginDto = this.login.toDto(),
    name = this.name,
    email = this.email,
    address = this.address,
    phone = this.phone,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun CompanyDto.toEntity() = Company(
    idCompany = idCompany,
    login = this.loginDto?.toEntity()!!,
    name = this.name!!,
    email = this.email!!,
    address = this.address!!,
    phone = this.phone!!,
    createdAt = this.createdAt!!,
    createdBy = this.createdBy!!,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
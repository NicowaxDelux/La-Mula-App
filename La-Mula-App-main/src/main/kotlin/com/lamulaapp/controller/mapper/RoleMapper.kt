package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.RoleDto
import com.lamulaapp.domain.Role

fun Role.toDto() = RoleDto(
    idRole = this.idRole,
    name = this.name,
    description = this.description,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun RoleDto.toEntity() = Role(
    idRole = this.idRole,
    name = this.name,
    description = this.description,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
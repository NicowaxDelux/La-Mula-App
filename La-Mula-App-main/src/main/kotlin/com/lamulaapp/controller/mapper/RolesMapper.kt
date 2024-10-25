package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.Roles
import com.lamulaapp.controller.dto.RoleDto

fun Roles.toDto(): RoleDto = RoleDto(
    idRole = this.idRole,
    name = this.name,
    description = this.description,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)

fun RoleDto.toEntity(): Roles = Roles(
    idRole = this.idRole,
    name = this.name,
    description = this.description,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)
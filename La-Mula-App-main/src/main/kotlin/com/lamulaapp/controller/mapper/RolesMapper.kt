package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.Roles
import com.lamulaapp.controller.dto.RolesDto

fun Roles.toDto(): RolesDto = RolesDto(
    idRole = this.idRole,
    name = this.name,
    description = this.description,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)

fun RolesDto.toEntity(): Roles = Roles(
    idRole = this.idRole,
    name = this.name,
    description = this.description,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)
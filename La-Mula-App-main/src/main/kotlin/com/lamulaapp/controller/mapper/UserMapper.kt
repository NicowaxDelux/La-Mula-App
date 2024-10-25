package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.UserDto
import com.lamulaapp.domain.User

fun User.toDto() = UserDto(
    idUser = this.idUser,
    roleDto = this.role?.toDto()!!,
    name = this.name,
    email = this.email,
    address = this.address,
    phone = this.phone,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun UserDto.toEntity() = User(
    idUser = idUser,
    role = this.roleDto.toEntity(),
    name = this.name,
    email = this.email,
    address = this.address,
    phone = this.phone,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
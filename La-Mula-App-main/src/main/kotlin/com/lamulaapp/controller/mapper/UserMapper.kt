package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.UserDto
import com.lamulaapp.domain.User

fun User.toDto() = UserDto(
    idUser = this.idUser,
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

fun UserDto.toEntity() = User(
    idUser = idUser,
    login = this.loginDto?.toEntity()!!,
    name = this.name!!,
    email = this.email!!,
    address = this.address,
    phone = this.phone,
    createdAt = this.createdAt!!,
    createdBy = this.createdBy!!,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.Login
import com.lamulaapp.controller.dto.LoginDto

fun Login.toDto(): LoginDto = LoginDto(
    id = this.idLogin,
    userDto = this.user.toDto(),
    username = this.username,
    password = this.password,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun LoginDto.toEntity(): Login = Login(
    idLogin = this.id,
    user = this.userDto?.toEntity()!!,
    username = this.username,
    password = this.password,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
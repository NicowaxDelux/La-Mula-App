package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.Login
import com.lamulaapp.controller.dto.LoginDto
import com.lamulaapp.domain.enums.RolesEnum

fun Login.toDto(): LoginDto = LoginDto(
    idLogin = this.idLogin,
    role = this.role.name,
    username = this.username,
    password = this.password,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun LoginDto.toEntity(): Login = Login(
    idLogin = this.idLogin,
    role = RolesEnum.valueOf(this.role!!),
    username = this.username!!,
    password = this.password!!,
    createdAt = this.createdAt!!,
    createdBy = this.createdBy!!,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
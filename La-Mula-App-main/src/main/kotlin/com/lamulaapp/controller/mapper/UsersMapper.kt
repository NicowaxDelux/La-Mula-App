package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.Users
import com.lamulaapp.controller.dto.UsersDto

fun Users.toDto(): UsersDto = UsersDto(
    id = this.id,
    idRole = this.idRole,
    name = this.name,
    email = this.email,
    address = this.address,
    phone = this.phone,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)

fun UsersDto.toEntity(): Users = Users(
    id = this.id,
    idRole = this.idRole,
    name = this.name,
    email = this.email,
    address = this.address,
    phone = this.phone,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)
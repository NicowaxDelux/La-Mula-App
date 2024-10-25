package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.Users
import com.lamulaapp.controller.dto.UserDto

fun Users.toDto(): UserDto = UserDto(
    id = this.id,
    roleDto = this.idRole,
    name = this.name,
    email = this.email,
    address = this.address,
    phone = this.phone,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)

fun UserDto.toEntity(): Users = Users(
    id = this.id,
    idRole = this.roleDto,
    name = this.name,
    email = this.email,
    address = this.address,
    phone = this.phone,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)
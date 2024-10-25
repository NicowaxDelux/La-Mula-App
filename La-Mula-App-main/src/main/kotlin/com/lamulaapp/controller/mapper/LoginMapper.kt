package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.Login
import com.lamulaapp.controller.dto.LoginDto

fun Login.toDto(): LoginDto = LoginDto(
    id = this.id,
    userDto = this.idUser,
    userName = this.userName,
    passWord = this.passWord,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)

fun LoginDto.toEntity(): Login = Login(
    id = this.id,
    idUser = this.userDto,
    userName = this.userName,
    passWord = this.passWord,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)
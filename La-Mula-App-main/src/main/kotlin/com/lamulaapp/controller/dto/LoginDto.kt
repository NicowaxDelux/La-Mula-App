package com.lamulaapp.controller.dto

import java.util.UUID
import java.time.LocalDateTime

data class LoginDto(
    val id: UUID? = null,
    val userDto: UserDto? = null,
    val userName: String,
    val passWord: String,
    val createDate: LocalDateTime,
    val createBy: String,
    val updateAt: LocalDateTime,
    val updateBy: String,
)

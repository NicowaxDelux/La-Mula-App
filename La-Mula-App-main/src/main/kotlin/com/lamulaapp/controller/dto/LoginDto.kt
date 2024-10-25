package com.lamulaapp.controller.dto

import com.lamulaapp.domain.Users
import java.util.UUID
import java.time.LocalDateTime

data class LoginDto(
    val id: UUID? = null,
    val idUser: Users? = null,
    val userName: String,
    val passWord: String,
    val createDate: LocalDateTime,
    val createBy: String,
    val updateAt: LocalDateTime,
    val updateBy: String,
)

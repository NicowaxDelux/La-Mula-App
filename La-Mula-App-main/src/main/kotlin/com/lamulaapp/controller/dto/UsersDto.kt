package com.lamulaapp.controller.dto

import com.lamulaapp.domain.Roles
import java.time.LocalDateTime
import java.util.UUID

data class UsersDto(
    val id: UUID? = null,
    val idRole: Roles? = null,
    val name: String,
    val email: String,
    val address: String,
    val phone: Int,
    val createDate: LocalDateTime,
    val createBy: String,
    val updateAt: LocalDateTime,
    val updateBy: String,
)

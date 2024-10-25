package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val id: UUID? = null,
    val roleDto: RoleDto? = null,
    val name: String,
    val email: String,
    val address: String,
    val phone: Int,
    val createDate: LocalDateTime,
    val createBy: String,
    val updateAt: LocalDateTime,
    val updateBy: String,
)

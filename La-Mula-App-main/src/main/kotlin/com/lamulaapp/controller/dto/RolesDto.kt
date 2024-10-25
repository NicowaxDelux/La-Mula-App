package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class RolesDto(
    val idRole: UUID? = null,
    val name: String,
    val description: String,
    val createDate: LocalDateTime,
    val createBy: String,
    val updateAt: LocalDateTime,
    val updateBy: String,
)
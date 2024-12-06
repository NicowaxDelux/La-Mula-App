package com.lamulaapp.controller.dto

import com.lamulaapp.domain.enums.RolesEnum
import java.util.UUID
import java.time.LocalDateTime

data class LoginDto(
    val idLogin: UUID? = null,
    val role: String? = null,
    val username: String? = null,
    val password: String? = null,
    val createdAt: LocalDateTime? = null,
    val createdBy: String? = null,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)

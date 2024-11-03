package com.lamulaapp.controller.dto

import java.util.UUID
import java.time.LocalDateTime

data class LoginDto(
    val id: UUID? = null,
    val userDto: UserDto? = null,
    val username: String,
    val password: String,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)

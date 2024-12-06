package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val idUser: UUID? = null,
    val loginDto: LoginDto? = null,
    val name: String? = null,
    val email: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val createdAt: LocalDateTime? = null,
    val createdBy: String? = null,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)

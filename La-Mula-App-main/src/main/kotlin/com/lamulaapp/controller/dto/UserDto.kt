package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val idUser: UUID? = null,
    val loginDto: LoginDto,
    val name: String,
    val email: String,
    val address: String,
    val phone: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val createdBy: String,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)

package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val idUser: UUID? = null,
    val roleDto: RoleDto,
    val name: String,
    val email: String,
    val address: String,
    val phone: String,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val updatedAt: LocalDateTime,
    val updatedBy: String
)

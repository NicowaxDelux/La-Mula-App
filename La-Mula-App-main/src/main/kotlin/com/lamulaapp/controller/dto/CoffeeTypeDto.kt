package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class CoffeeTypeDto(
    val idCoffeeType: UUID? = null,
    val name: String,
    val description: String,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val updatedAt: LocalDateTime,
    val updatedBy: String
)
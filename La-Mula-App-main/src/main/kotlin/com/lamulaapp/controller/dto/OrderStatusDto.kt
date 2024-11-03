package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class OrderStatusDto(
    val idOrderStatus: UUID? = null,
    val typeStatus: String,
    val description: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val createdBy: String,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)

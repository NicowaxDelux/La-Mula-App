package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.UUID

data class OrderStatusDto(
    val idOrderStatus: UUID? = null,
    val typeStatus: String? = null,
    val description: String? = null,
    val createdAt: LocalDateTime? = null,
    val createdBy: String? = null,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)

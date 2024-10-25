package com.lamulaapp.controller.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OrderDto(
    val idOrder: UUID? = null,
    val userDto: UserDto,
    val orderStatusDto: OrderStatusDto,
    val orderCode: Long? = null,
    val totalCost: BigDecimal,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val updatedAt: LocalDateTime,
    val updatedBy: String
)

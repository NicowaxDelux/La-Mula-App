package com.lamulaapp.controller.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OrderDetailDto(
    val idOrderDetail: UUID? = null,
    val orderDto: OrderDto,
    val productDto: ProductDto,
    val quantity: Int,
    val unitCost: BigDecimal,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val updatedAt: LocalDateTime,
    val updatedBy: String
)
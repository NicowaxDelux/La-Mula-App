package com.lamulaapp.controller.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OrderDetailDto(
    val idOrderDetail: UUID? = null,
    val orderDto: OrderDto? = null,
    val productDto: ProductDto? = null,
    val quantity: Int? = null,
    val unitCost: BigDecimal? = null,
    val createdAt: LocalDateTime? = null,
    val createdBy: String? = null,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)
package com.lamulaapp.controller.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OrderDto(
    val idOrder: UUID? = null,
    val userDto: UserDto? = null,
    val companyDto: CompanyDto? = null,
    val orderStatusDto: OrderStatusDto? = null,
    val orderCode: Long? = null,
    val totalCost: BigDecimal? = null,
    val createdAt: LocalDateTime? = null,
    val createdBy: String? = null,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)

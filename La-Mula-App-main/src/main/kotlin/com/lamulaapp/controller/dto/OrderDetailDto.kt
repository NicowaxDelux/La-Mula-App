package com.lamulaapp.controller.dto

import java.math.BigDecimal
import java.util.UUID

data class OrderDetailDto(
    val idOrderDetail: UUID? = null,
    val idOrder: UUID,
    val idProduct: UUID,
    val quantity: Int,
    val unitCost: BigDecimal
)
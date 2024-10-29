package com.lamulaapp.controller.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class ProductDto(
    val idProduct: UUID? = null,
    val name: String,
    val coffeeTypeDto: CoffeeTypeDto? = null,
    val attachmentDto: AttachmentDto,
    val description: String? = null,
    val unitCost: BigDecimal,
    val quantity: Int,
    val packageSize: String,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)
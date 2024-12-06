package com.lamulaapp.controller.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class ProductDto(
    val idProduct: UUID? = null,
    val name: String? = null,
    val coffeeTypeDto: CoffeeTypeDto? = null,
    val attachmentDto: AttachmentDto? = null,
    val companyDto: CompanyDto? = null,
    val description: String? = null,
    val unitCost: BigDecimal? = null,
    val stock: Int? = null,
    val packageSize: String? = null,
    val createdAt: LocalDateTime? = null,
    val createdBy: String? = null,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)
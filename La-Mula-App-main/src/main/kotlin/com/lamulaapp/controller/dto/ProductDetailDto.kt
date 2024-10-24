package com.lamulaapp.controller.dto

import java.util.UUID

data class ProductDetailDto(
    val idProductDetail: UUID? = null,
    val name: String,
    val packageSize: String,
    val idAttachment: UUID? = null // Asumo que necesita el ID del Attachment
)
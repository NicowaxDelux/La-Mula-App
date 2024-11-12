package com.lamulaapp.controller.dto

import java.util.*

data class CheckoutRequestDto(
    val idEntity: UUID? = null,
    val username: String? = null,
    val rolName: String? = null,
    val productQuantityList: List<ProductQuantityDto>? = null
)

data class ProductQuantityDto(
    val productDto: ProductDto,
    val quantity: Int
)

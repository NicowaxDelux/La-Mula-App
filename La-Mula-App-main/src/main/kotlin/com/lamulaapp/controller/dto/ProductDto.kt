package com.lamulaapp.controller.dto

import java.util.UUID

data class ProductDto(
    val idProduct: UUID? = null,
    val name: String,
    val idCoffeeType: UUID,  // Usaremos el ID del CoffeeType en el DTO
    val description: String? = null,
    val unitCost: Double,
    val quantity: Int
)
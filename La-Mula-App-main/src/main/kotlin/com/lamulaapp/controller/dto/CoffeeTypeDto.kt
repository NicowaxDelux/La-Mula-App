package com.lamulaapp.controller.dto

import java.util.UUID

data class CoffeeTypeDto(
    val id: UUID? = null,
    val name: String,
    val description: String
)
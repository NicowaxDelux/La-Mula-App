package com.lamulaapp.controller.dto

import java.util.*

data class SignUpResponseDto(
    val idEntity: UUID,
    val name: String,
    val username: String,
    val roleName: String
)
package com.lamulaapp.controller.dto

import java.util.*

interface LoginResponseDto {
    val idEntity: UUID
    val name: String
    val username: String
    val roleName: String
}
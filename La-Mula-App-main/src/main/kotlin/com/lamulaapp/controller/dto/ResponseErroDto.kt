package com.lamulaapp.controller.dto

import java.time.LocalDateTime

class ResponseErrorDto(
    val message: String,
    val statusCode: Int,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
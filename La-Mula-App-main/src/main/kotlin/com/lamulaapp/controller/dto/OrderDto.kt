package com.lamulaapp.controller.dto

import com.lamulaapp.domain.OrderStatus
import com.lamulaapp.domain.Users
import java.io.Serial
import java.time.LocalDateTime
import java.util.UUID

data class OrderDto(
    val id: UUID? = null,
    val userDto: Users? = null,
    val orderStatusDto: OrderStatus? = null,
    val orderCode: Serial,
    val totalCost: Float,
    val createDate: LocalDateTime,
    val createBy: String,
    val updateAt: LocalDateTime,
    val updateBy: String,
)

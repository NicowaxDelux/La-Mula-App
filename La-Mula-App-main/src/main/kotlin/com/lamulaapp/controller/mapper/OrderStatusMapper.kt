package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.OrderStatusDto
import com.lamulaapp.domain.OrderStatus

fun OrderStatus.toDto() = OrderStatusDto(
    idOrderStatus = this.idOrderStatus,
    typeStatus = this.typeStatus,
    description = this.description,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun OrderStatusDto.toEntity() = OrderStatus(
    idOrderStatus = this.idOrderStatus,
    typeStatus = this.typeStatus,
    description = this.description,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
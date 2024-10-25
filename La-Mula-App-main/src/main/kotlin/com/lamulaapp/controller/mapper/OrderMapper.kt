package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.OrderDto
import com.lamulaapp.domain.Order

fun Order.toDto() = OrderDto(
    idOrder = this.idOrder,
    userDto = this.user.toDto(),
    orderStatusDto = this.orderStatus.toDto(),
    orderCode = this.orderCode,
    totalCost = this.totalCost,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun OrderDto.toEntity() = Order(
    idOrder = this.idOrder,
    user = this.userDto.toEntity(),
    orderStatus = this.orderStatusDto.toEntity(),
    orderCode = this.orderCode,
    totalCost = this.totalCost,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
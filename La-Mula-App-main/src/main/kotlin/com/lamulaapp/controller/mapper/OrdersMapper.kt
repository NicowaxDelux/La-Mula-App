package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.Orders
import com.lamulaapp.controller.dto.OrderDto

fun Orders.toDto(): OrderDto = OrderDto(
    id = this.id,
    userDto = this.idUser,
    orderStatusDto = this.idOrderStatus,
    orderCode = this.orderCode,
    totalCost = this.totalCost,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)

fun OrderDto.toEntity(): Orders = Orders(
    id = this.id,
    idUser = this.userDto,
    idOrderStatus = this.orderStatusDto,
    orderCode = this.orderCode,
    totalCost = this.totalCost,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)
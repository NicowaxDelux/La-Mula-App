package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.Orders
import com.lamulaapp.controller.dto.OrdersDto

fun Orders.toDto(): OrdersDto = OrdersDto(
    id = this.id,
    idUser = this.idUser,
    idOrderStatus = this.idOrderStatus,
    orderCode = this.orderCode,
    totalCost = this.totalCost,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)

fun OrdersDto.toEntity(): Orders = Orders(
    id = this.id,
    idUser = this.idUser,
    idOrderStatus = this.idOrderStatus,
    orderCode = this.orderCode,
    totalCost = this.totalCost,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)
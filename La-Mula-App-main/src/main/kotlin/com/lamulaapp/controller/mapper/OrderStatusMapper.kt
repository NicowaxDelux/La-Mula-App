package com.lamulaapp.controller.mapper

import com.lamulaapp.domain.OrderStatus
import com.lamulaapp.controller.dto.OrderStatusDto

fun OrderStatus.toDto(): OrderStatusDto = OrderStatusDto(
    id = this.id,
    name = this.name,
    description = this.description,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)

fun OrderStatusDto.toEntity(): OrderStatus = OrderStatus(
    id = this.id,
    name = this.name,
    description = this.description,
    createDate = this.createDate,
    createBy = this.createBy,
    updateAt = this.updateAt,
    updateBy = this.updateBy
)
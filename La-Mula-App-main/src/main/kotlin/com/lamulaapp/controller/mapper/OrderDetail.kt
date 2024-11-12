package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.OrderDetailDto
import com.lamulaapp.domain.OrderDetail

fun OrderDetail.toDto() = OrderDetailDto(
    idOrderDetail = this.idOrderDetail,
    orderDto = this.order.toDto(),
    productDto = this.product.toDto(),
    quantity = this.quantity,
    unitCost = this.unitCost,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun OrderDetailDto.toEntity() = OrderDetail(
    idOrderDetail = this.idOrderDetail,
    order = this.orderDto?.toEntity()!!,
    product = this.productDto?.toEntity()!!,
    quantity = this.quantity!!,
    unitCost = this.unitCost!!,
    createdAt = this.createdAt!!,
    createdBy = this.createdBy!!,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
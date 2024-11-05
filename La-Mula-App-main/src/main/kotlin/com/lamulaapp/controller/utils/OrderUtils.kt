package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.OrderDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

fun validateCreateOrder(orderDto: OrderDto): ValidationResult<OrderDto> {
    val validateOrder = Validation {
        OrderDto::orderCode required {}
        OrderDto::totalCost required {}
        OrderDto::createdAt required {}
        OrderDto::createdBy required {}
    }
    return validateOrder(orderDto)
}

fun validateUpdateOrder(orderDto: OrderDto): ValidationResult<OrderDto> {
    val validateOrder = Validation {
        OrderDto::idOrder required {}
        OrderDto::orderCode required {}
        OrderDto::totalCost required {}
        OrderDto::createdAt required {}
        OrderDto::createdBy required {}
        OrderDto::updatedBy required {}
    }
    return validateOrder(orderDto)
}
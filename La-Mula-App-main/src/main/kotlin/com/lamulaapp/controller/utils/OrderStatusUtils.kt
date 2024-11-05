package com.lamulaapp.controller.utils

import  com.lamulaapp.controller.dto.OrderStatusDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

fun validateCreateOrderStatus(orderStatusDto: OrderStatusDto): ValidationResult<OrderStatusDto> {
    val validateOrderStatus = Validation {
        OrderStatusDto::typeStatus required {}
        OrderStatusDto::description required {}
        OrderStatusDto::createdAt required {}
        OrderStatusDto::createdBy required {}
    }

    return validateOrderStatus(orderStatusDto)
}

fun validateUpdateOrderStatus(orderStatusDto: OrderStatusDto): ValidationResult<OrderStatusDto> {
    val validateOrderStatus = Validation {
        OrderStatusDto::idOrderStatus required {}
        OrderStatusDto::typeStatus required {}
        OrderStatusDto::description required {}
        OrderStatusDto::createdAt required {}
        OrderStatusDto::createdBy required {}
        OrderStatusDto::updatedBy required {}
    }

    return validateOrderStatus(orderStatusDto)
}


package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.OrderDetailDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

fun validateCreateOrderDetail(orderDetailDto: OrderDetailDto): ValidationResult<OrderDetailDto> {
    val validateOrderDetail = Validation {
        OrderDetailDto::orderDto required {}
        OrderDetailDto::productDto required {}
        OrderDetailDto::quantity required {}
        OrderDetailDto::unitCost required {}
        OrderDetailDto::createdAt required {}
        OrderDetailDto::createdBy required {}
    }
    return validateOrderDetail(orderDetailDto)
}

fun validateUpdateOrderDetail(orderDetailDto: OrderDetailDto): ValidationResult<OrderDetailDto> {
    val validateOrderDetail = Validation {
        OrderDetailDto::idOrderDetail required {}
        OrderDetailDto::orderDto required {}
        OrderDetailDto::productDto required {}
        OrderDetailDto::quantity required {}
        OrderDetailDto::unitCost required {}
        OrderDetailDto::createdAt required {}
        OrderDetailDto::createdBy required {}
        OrderDetailDto::updatedBy required {}
    }
    return validateOrderDetail(orderDetailDto)
}
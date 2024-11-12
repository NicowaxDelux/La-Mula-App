package com.lamulaapp.controller.utils

import com.lamulaapp.controller.dto.CheckoutRequestDto
import com.lamulaapp.controller.dto.ProductQuantityDto
import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.minItems
import java.math.BigDecimal

fun validateCheckout(checkoutRequestDto: CheckoutRequestDto): ValidationResult<CheckoutRequestDto> {
    val validateCheckout = Validation {
        CheckoutRequestDto::idEntity required {}
        CheckoutRequestDto::username required {}
        CheckoutRequestDto::rolName required {}
        CheckoutRequestDto::productQuantityList required {
            minItems(1)
        }
    }
    return validateCheckout(checkoutRequestDto)
}

fun calculateTotalCost(productQuantityList: List<ProductQuantityDto>): BigDecimal {
    return productQuantityList.sumOf { item ->
        item.productDto.unitCost!! * item.quantity.toBigDecimal()
    }
}
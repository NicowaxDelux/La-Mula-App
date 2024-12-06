package com.lamulaapp.controller

import com.lamulaapp.controller.dto.CheckoutRequestDto
import com.lamulaapp.controller.dto.CheckoutResponseDto
import com.lamulaapp.controller.utils.validateCheckout
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.CheckoutService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckoutController(
    private val checkoutService: CheckoutService
) {

    @PostMapping("/checkout")
    @Operation(
        summary = "Add all products to purchase in a list",
        description = "This endpoint allows listing all products with details, such as the product name, unit cost, and the quantity the user wants to order."
    )
    fun checkout(@RequestBody checkoutRequestDto: CheckoutRequestDto): ResponseEntity<CheckoutResponseDto> {
        val validation = validateCheckout(checkoutRequestDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }
        return ResponseEntity(checkoutService.checkout(checkoutRequestDto), HttpStatus.CREATED)
    }
}
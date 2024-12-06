package com.lamulaapp.controller

import com.lamulaapp.controller.dto.OrderDetailDto
import com.lamulaapp.controller.utils.validateCreateOrderDetail
import com.lamulaapp.controller.utils.validateUpdateOrderDetail
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.OrderDetailService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class OrderDetailController(
    private val orderDetailService: OrderDetailService
) {

    @PostMapping("/ordersDetails")
    @Operation(
        summary = "Allows create the order detail.",
        description = "creates the order detail and stores it in the database."
    )
    fun createOrderDetail(@RequestBody orderDetailDto: OrderDetailDto): ResponseEntity<OrderDetailDto> {
        val validation = validateCreateOrderDetail(orderDetailDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(orderDetailService.createOrderDetail(orderDetailDto), HttpStatus.CREATED)
    }

    @GetMapping("/ordersDetails")
    @Operation(
        summary = "List all order details.",
        description = "can list all the details."
    )
    fun getAllOrderDetail():ResponseEntity<List<OrderDetailDto>> {
        return ResponseEntity(orderDetailService.getOrderDetails(), HttpStatus.OK)
    }

    @GetMapping("/ordersDetails/{id}")
    @Operation(
        summary = "List order detail by ID",
        description = "Only the detail id of the order passed by the user is listed."
    )
    fun getOrderDetailById(@PathVariable("id") id: UUID): ResponseEntity<OrderDetailDto> {
        return ResponseEntity(orderDetailService.getOrderDetailById(id), HttpStatus.OK)
    }

    @PutMapping("/ordersDetails/{id}")
    @Operation(
        summary = "allows you to update the order details",
        description = "It is updated once the order detail id has been passed to it."
    )
    fun updateOrderDetail(@PathVariable("id") id: UUID, @RequestBody orderDetailDto: OrderDetailDto): ResponseEntity<OrderDetailDto> {
        val validate = validateUpdateOrderDetail(orderDetailDto)

        if (!validate.isValid) {
            throw ValidationErrorsException(validate.errors)
        }

        return ResponseEntity(orderDetailService.updateOrderDetail(id, orderDetailDto), HttpStatus.OK)
    }

    @DeleteMapping("/ordersDetails/{id}")
    @Operation(
        summary = "Allows delete the order detail.",
        description = "Delete the order detail by ID."
    )
    fun deleteOrderDetail(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(orderDetailService.deleteOrderDetail(id), HttpStatus.NO_CONTENT)
    }
}
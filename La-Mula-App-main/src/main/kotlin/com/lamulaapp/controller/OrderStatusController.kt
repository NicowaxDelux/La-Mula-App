package com.lamulaapp.controller

import com.lamulaapp.service.OrderStatusService
import com.lamulaapp.controller.dto.OrderStatusDto
import com.lamulaapp.controller.utils.validateCreateOrderStatus
import com.lamulaapp.controller.utils.validateUpdateOrderStatus
import com.lamulaapp.domain.OrderStatus
import com.lamulaapp.exception.ValidationErrorsException
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class OrderStatusController(
    private val orderStatusService: OrderStatusService
) {
    @PostMapping("/ordersStatuses")
    @Operation(
        summary = "Allows create order status.",
        description = "Can create a new order status."
    )
    fun createOrderStatus(@RequestBody orderStatusDto: OrderStatusDto): ResponseEntity<OrderStatusDto> {
        val validation = validateCreateOrderStatus(orderStatusDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(orderStatusService.createOrderStatus(orderStatusDto), HttpStatus.CREATED)
    }

    @GetMapping("/ordersStatuses")
    @Operation(
        summary = "List all orders statuses.",
        description = "List all order Statuses."
    )
    fun getOrderStatus(): ResponseEntity<List<OrderStatusDto>> {
        return ResponseEntity(orderStatusService.getOrderStatus(), HttpStatus.OK)
    }

    @GetMapping("/ordersStatuses/{id}")
    @Operation(
        summary = "List order status by ID.",
        description = "You can list the status order that the user wants by passing the ID"
    )
    fun getOrderStatusById(@PathVariable("id") id:UUID): ResponseEntity<OrderStatusDto> {
        return ResponseEntity(orderStatusService.getOrderStatusById(id), HttpStatus.OK)
    }

    @PutMapping("/ordersStatuses/{id}")
    @Operation(
        summary = "Allows to update the status order",
        description = "Updates the status order by passing it the id"
    )
    fun updateOrderStatus(@PathVariable("id") id:UUID, @RequestBody orderStatusDto: OrderStatusDto):ResponseEntity<OrderStatusDto> {
        val validate = validateUpdateOrderStatus(orderStatusDto)

        if (!validate.isValid) {
            throw ValidationErrorsException(validate.errors)
        }

        return ResponseEntity(orderStatusService.updateOrderStatus(id, orderStatusDto), HttpStatus.OK)
    }

    @DeleteMapping("/ordersStatuses/{id}")
    @Operation(
        summary = "Delete order status",
        description = "Allows delete the order status by ID."
    )
    fun deleteOrderStatus(@PathVariable("id") id:UUID): ResponseEntity<Unit> {
        return ResponseEntity(orderStatusService.deleteOrderStatus(id), HttpStatus.NO_CONTENT)
    }
}
package com.lamulaapp.controller

import com.lamulaapp.controller.dto.OrderDto
import com.lamulaapp.controller.dto.ProductDto
import com.lamulaapp.controller.utils.validateCreateOrder
import com.lamulaapp.controller.utils.validateUpdateOrder
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping("/orders")
    @Operation(
        summary = "Allows you to create an order.",
        description = "Once the order is created, it is storage in the database."
    )
    fun createOrder(@RequestBody orderDto: OrderDto): ResponseEntity<OrderDto> {
        val validation = validateCreateOrder(orderDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(orderService.createOrder(orderDto), HttpStatus.CREATED)
    }

    @GetMapping("/orders")
    @Operation(
        summary = "List the orders.",
        description = "Lists all orders found in the database. "
    )
    fun getOrders():ResponseEntity<List<OrderDto>> {
        return ResponseEntity(orderService.getOrders(), HttpStatus.OK)
    }

    @GetMapping("/orders/{id}")
    @Operation(
        summary = "List the order by ID provide by the user.",
        description = "Allows the user to find the order by ID more quickly."
    )
    fun getOrderById(@PathVariable("id") id:UUID): ResponseEntity<OrderDto> {
        return ResponseEntity(orderService.getOrderId(id), HttpStatus.OK)
    }

    @GetMapping("/orders/{id}/details")
    @Operation(
        summary = "Get the order details by order ID.",
        description = "You can get all the specific details of the order searched by the ID."
    )
    fun getOrderDetailsByOrderId(@PathVariable("id") id: UUID): ResponseEntity<List<ProductDto>> {
        return ResponseEntity(orderService.getProductsByOrderId(id), HttpStatus.OK)
    }

    @PutMapping("/orders/{id}")
    @Operation(
        summary = "The user can update the order",
        description = "Using the entered order ID you can update it"
    )
    fun updateOrder(@PathVariable("id") id: UUID, @RequestBody orderDto: OrderDto): ResponseEntity<OrderDto> {
        val validation = validateUpdateOrder(orderDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(orderService.updateOrder(id, orderDto), HttpStatus.OK)
    }

    @DeleteMapping("/orders/{id}")
    @Operation(
        summary = "Allows delete order.",
        description = "By entering the order ID, it will be deleted from the database."
    )
    fun deleteOrder(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(orderService.deleteOrder(id), HttpStatus.NO_CONTENT)
    }
}
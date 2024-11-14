package com.lamulaapp.controller

import com.lamulaapp.controller.dto.OrderDto
import com.lamulaapp.controller.dto.ProductDto
import com.lamulaapp.controller.utils.validateCreateOrder
import com.lamulaapp.controller.utils.validateUpdateOrder
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping("/orders")
    fun createOrder(@RequestBody orderDto: OrderDto): ResponseEntity<OrderDto> {
        val validation = validateCreateOrder(orderDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(orderService.createOrder(orderDto), HttpStatus.CREATED)
    }

    @GetMapping("/orders")
    fun getOrders():ResponseEntity<List<OrderDto>> {
        return ResponseEntity(orderService.getOrders(), HttpStatus.OK)
    }

    @GetMapping("/orders/{id}")
    fun getOrderById(@PathVariable("id") id:UUID): ResponseEntity<OrderDto> {
        return ResponseEntity(orderService.getOrderId(id), HttpStatus.OK)
    }

    @GetMapping("/orders/{id}/details")
    fun getOrderDetailsByOrderId(@PathVariable("id") id:UUID): ResponseEntity<List<ProductDto>> {
        return ResponseEntity(orderService.getProductsByOrderId(id), HttpStatus.OK)
    }


    @PutMapping("/orders/{id}")
    fun updateOrder(@PathVariable("id") id: UUID, @RequestBody orderDto: OrderDto): ResponseEntity<OrderDto> {
        val validation = validateUpdateOrder(orderDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(orderService.updateOrder(id, orderDto), HttpStatus.OK)
    }

    @DeleteMapping("/orders/{id}")
    fun deleteOrder(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(orderService.deleteOrder(id), HttpStatus.NO_CONTENT)
    }
}
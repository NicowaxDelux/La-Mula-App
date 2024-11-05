package com.lamulaapp.controller

import com.lamulaapp.service.OrderStatusService
import com.lamulaapp.controller.dto.OrderStatusDto
import com.lamulaapp.controller.utils.validateCreateOrderStatus
import com.lamulaapp.controller.utils.validateUpdateOrderStatus
import com.lamulaapp.domain.OrderStatus
import com.lamulaapp.exception.ValidationErrorsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class OrderStatusController(
    private val orderStatusService: OrderStatusService
) {
    @PostMapping("/ordersStatuses")
    fun createOrderStatus(@RequestBody orderStatusDto: OrderStatusDto): ResponseEntity<OrderStatusDto> {
        val validation = validateCreateOrderStatus(orderStatusDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(orderStatusService.createOrderStatus(orderStatusDto), HttpStatus.CREATED)
    }

    @GetMapping("/ordersStatuses")
    fun getOrderStatus(): ResponseEntity<List<OrderStatusDto>> {
        return ResponseEntity(orderStatusService.getOrderStatus(), HttpStatus.OK)
    }

    @GetMapping("/ordersStatuses/{id}")
    fun getOrderStatusById(@PathVariable("id") id:UUID): ResponseEntity<OrderStatusDto> {
        return ResponseEntity(orderStatusService.getOrderStatusById(id), HttpStatus.OK)
    }

    @PutMapping("/ordersStatuses/{id}")
    fun updateOrderStatus(@PathVariable("id") id:UUID, @RequestBody orderStatusDto: OrderStatusDto):ResponseEntity<OrderStatusDto> {
        val validate = validateUpdateOrderStatus(orderStatusDto)

        if (!validate.isValid) {
            throw ValidationErrorsException(validate.errors)
        }

        return ResponseEntity(orderStatusService.updateOrderStatus(id, orderStatusDto), HttpStatus.OK)
    }

    @DeleteMapping("/ordersStatuses/{id}")
    fun deleteOrderStatus(@PathVariable("id") id:UUID): ResponseEntity<Unit> {
        return ResponseEntity(orderStatusService.deleteOrderStatus(id), HttpStatus.NO_CONTENT)
    }
}
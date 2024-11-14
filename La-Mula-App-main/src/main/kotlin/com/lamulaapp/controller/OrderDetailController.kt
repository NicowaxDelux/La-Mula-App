package com.lamulaapp.controller

import com.lamulaapp.controller.dto.OrderDetailDto
import com.lamulaapp.controller.utils.validateCreateOrderDetail
import com.lamulaapp.controller.utils.validateUpdateOrderDetail
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.OrderDetailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class OrderDetailController(
    private val orderDetailService: OrderDetailService
) {

    @PostMapping("/ordersDetails")
    fun createOrderDetail(@RequestBody orderDetailDto: OrderDetailDto): ResponseEntity<OrderDetailDto> {
        val validation = validateCreateOrderDetail(orderDetailDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(orderDetailService.createOrderDetail(orderDetailDto), HttpStatus.CREATED)
    }

    @GetMapping("/ordersDetails")
    fun getAllOrderDetail():ResponseEntity<List<OrderDetailDto>> {
        return ResponseEntity(orderDetailService.getOrderDetails(), HttpStatus.OK)
    }

    @GetMapping("/ordersDetails/{id}")
    fun getOrderDetailById(@PathVariable("id") id: UUID): ResponseEntity<OrderDetailDto> {
        return ResponseEntity(orderDetailService.getOrderDetailById(id), HttpStatus.OK)
    }

    @PutMapping("/ordersDetails/{id}")
    fun updateOrderDetail(@PathVariable("id") id: UUID, @RequestBody orderDetailDto: OrderDetailDto): ResponseEntity<OrderDetailDto> {
        val validate = validateUpdateOrderDetail(orderDetailDto)

        if (!validate.isValid) {
            throw ValidationErrorsException(validate.errors)
        }

        return ResponseEntity(orderDetailService.updateOrderDetail(id, orderDetailDto), HttpStatus.OK)
    }

    @DeleteMapping("/ordersDetails/{id}")
    fun deleteOrderDetail(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(orderDetailService.deleteOrderDetail(id), HttpStatus.NO_CONTENT)
    }
}
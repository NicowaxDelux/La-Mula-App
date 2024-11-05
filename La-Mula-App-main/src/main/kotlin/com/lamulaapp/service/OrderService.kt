package com.lamulaapp.service


import com.lamulaapp.controller.dto.OrderDto
import com.lamulaapp.repository.OrderRepository
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class OrderService (
    private val orderRepository: OrderRepository
){
    fun createOrder(orderDto: OrderDto): OrderDto {
        val findById = orderDto.idOrder?.let { orderRepository.findById(it) }

        if (findById != null && findById.isPresent) {
            throw  DuplicateKeyException("This ID already exist in the data base.")
        }

        return orderRepository.save(orderDto.toEntity()).toDto()
    }

    fun getOrders(): List<OrderDto> {
        return orderRepository.findAll().map { it.toDto() }
    }

    fun getOrderId(id: UUID): OrderDto {
        val response = orderRepository.findById(id)

        if (!response.isPresent) {
            throw DuplicateKeyException("This ID doesn't exist in the data base")
        }
        return response.get().toDto()
    }

    fun updateOrder(id: UUID, orderDto: OrderDto): OrderDto {
        val response = orderRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("This ID doesn't exist,please try again.")
        }

        if (id != orderDto.idOrder) {
            throw KeysAreDifferentException("This ID doesn't exist, please try again.")
        }

        if (orderDto.updatedBy == null){
            throw IllegalArgumentException("The field 'updatedBy' is mandatory!.")
        }

        val updateDate = orderDto
            .toEntity()
            .copy(updatedAt = LocalDateTime.now(), updatedBy = orderDto.updatedBy)

        return orderRepository.save(updateDate).toDto()
    }

    fun deleteOrder(id: UUID) {
        val findById = orderRepository.findById(id)

        if (!findById.isPresent) {
            throw EntityNotFoundException("This ID doesn't exist, please try again.")
        }
        orderRepository.deleteById(id)
    }


}
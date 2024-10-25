package com.lamulaapp.service

import com.lamulaapp.controller.dto.OrderDetailDto
import com.lamulaapp.controller.mapper.toDTO
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.repository.OrderDetailRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderDetailService(
    private val orderDetailRepository: OrderDetailRepository
) {

    fun createOrderDetail(orderDetailDto: OrderDetailDto): OrderDetailDto {
        val response = orderDetailRepository.save(orderDetailDto.toEntity())
        return response.toDTO()
    }

    fun getOrderDetails(): List<OrderDetailDto> {
        return orderDetailRepository.findAll().map { it.toDTO() }
    }

    fun getOrderDetailById(id: UUID): OrderDetailDto? {
        val response = orderDetailRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDTO()
        } else {
            null
        }
    }

    fun updateOrderDetail(id : UUID, orderDetailDto: OrderDetailDto): OrderDetailDto? {
        val response = orderDetailRepository.findById(id)

        if (!response.isPresent) {
            return null
        }

        if (id != orderDetailDto.id) {
            return null
        }
        return orderDetailRepository.save(orderDetailDto.toEntity()).toDTO()
    }

    fun deleteOrderDetail(id: UUID) {
        orderDetailRepository.deleteById(id)
    }
}
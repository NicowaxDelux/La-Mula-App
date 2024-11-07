package com.lamulaapp.service

import com.lamulaapp.controller.dto.OrderDetailDto
import com.lamulaapp.repository.OrderDetailRepository
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class OrderDetailService(
    private val orderDetailRepository: OrderDetailRepository
) {

    fun createOrderDetail(orderDetailDto: OrderDetailDto): OrderDetailDto {
        val findById = orderDetailDto.idOrderDetail?.let { orderDetailRepository.findById(it)}

        if (findById != null && findById.isPresent) {
            throw DuplicateKeyException("This ID already exist.")
        }

        val response = orderDetailRepository.save(orderDetailDto.toEntity())
        return response.toDto()
    }

    fun getOrderDetails(): List<OrderDetailDto> {
        return orderDetailRepository.findAll().map { it.toDto() }
    }

    fun getOrderDetailById(id: UUID): OrderDetailDto? {
        val response = orderDetailRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("Sorry, this ID doesn't exist.")
        }

        return response.get().toDto()
    }

    fun updateOrderDetail(id : UUID, orderDetailDto: OrderDetailDto): OrderDetailDto? {
        val response = orderDetailRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("This ID doesn't exist, please try again.")
        }

        if (id != orderDetailDto.idOrderDetail) {
            throw KeysAreDifferentException("This ID doesn't exist, please try again.")
        }

        if (orderDetailDto.updatedBy == null) {
            throw IllegalArgumentException("The field 'updatedBy' is mandatory!.")
        }

        val updateDate = orderDetailDto
            .toEntity()
            .copy(updatedAt = LocalDateTime.now(), updatedBy = orderDetailDto.updatedBy)

        return orderDetailRepository.save(updateDate).toDto()
    }

    fun deleteOrderDetail(id: UUID) {
        val findById = orderDetailRepository.findById(id)

        if (!findById.isPresent) {
            throw EntityNotFoundException("This ID doesn't exist, please try again.")
        }

        orderDetailRepository.deleteById(id)
    }
}
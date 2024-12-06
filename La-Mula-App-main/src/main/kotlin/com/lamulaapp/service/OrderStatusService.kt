package com.lamulaapp.service;

import com.lamulaapp.repository.OrderStatusRepository
import com.lamulaapp.controller.dto.OrderStatusDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class OrderStatusService(
        private val orderStatusRepository: OrderStatusRepository
){
        fun createOrderStatus(orderStatusDto: OrderStatusDto): OrderStatusDto {
                val findById = orderStatusDto.idOrderStatus?.let { orderStatusRepository.findById(it) }

                if (findById != null && findById.isPresent) {
                        throw DuplicateKeyException("The ID already exist in the database.")
                }

                val findByType = orderStatusRepository.findByTypeStatus(orderStatusDto.typeStatus!!)

                if (findByType.isPresent) {
                        throw DuplicateKeyException("The type: ${orderStatusDto.typeStatus} already exist.")
                }

                return orderStatusRepository.save(orderStatusDto.toEntity()).toDto()
        }

        fun getOrderStatus(): List<OrderStatusDto> {
                return orderStatusRepository.findAll().map { it.toDto() }
        }

        fun getOrderStatusById(id: UUID): OrderStatusDto{
                val response = orderStatusRepository.findById(id)

                if (!response.isPresent){
                        throw EntityNotFoundException("Sorry, this ID doesn't exist.")
                }
                return response.get().toDto()
        }

        fun updateOrderStatus(id: UUID, orderStatusDto: OrderStatusDto): OrderStatusDto {
                val findById = orderStatusRepository.findById(id)

                if (!findById.isPresent) {
                        throw EntityNotFoundException("This ID doesn't exist in the database.")
                }

                if (id != orderStatusDto.idOrderStatus) {
                        throw KeysAreDifferentException("This ID doesn't exist, please try again.")
                }

                if (orderStatusDto.updatedBy == null) {
                        throw IllegalArgumentException("The field 'updateBy' is mandatory!.")
                }

                val updateDate = orderStatusDto
                        .toEntity()
                        .copy(updatedAt = LocalDateTime.now(), updatedBy = orderStatusDto.updatedBy)

                return orderStatusRepository.save(updateDate).toDto()
        }

        fun deleteOrderStatus(id: UUID) {
                val response = orderStatusRepository.findById(id)

                if (!response.isPresent) {
                        throw EntityNotFoundException("This ID doesn't exist, please try again.")
                }
                orderStatusRepository.deleteById(id)
        }
}

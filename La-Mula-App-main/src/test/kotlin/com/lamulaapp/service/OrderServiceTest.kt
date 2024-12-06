package com.lamulaapp.service

import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.datamock.getOrderWithUserDto
import com.lamulaapp.datamock.getProduct1
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.repository.OrderDetailRepository
import com.lamulaapp.repository.OrderRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*
import kotlin.test.assertEquals

class OrderServiceTest {

    private val orderRepositoryMock = mockk<OrderRepository>()
    private val orderDetailRepositoryMock = mockk<OrderDetailRepository>()
    private val orderService = OrderService(orderRepositoryMock, orderDetailRepositoryMock)

    companion object {
        val orderUserDto = getOrderWithUserDto()
    }

    @Test
    fun `should create a new order successfully`() {
        // Given
        every { orderRepositoryMock.findById(any()) } returns Optional.empty()
        every { orderRepositoryMock.save(any()) } returns orderUserDto.toEntity()

        // When
        val result = orderService.createOrder(orderUserDto)

        // Then
        assertEquals(orderUserDto, result)
        verify(exactly = 1) { orderRepositoryMock.findById(any()) }
        verify(exactly = 1) { orderRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws a DuplicateKeyException when creating a new order`() {
        // Given
        every { orderRepositoryMock.findById(any()) } returns Optional.of(orderUserDto.toEntity())
        every { orderRepositoryMock.save(any()) } returns orderUserDto.toEntity()

        // When - Then
        assertThrows<DuplicateKeyException>{ orderService.createOrder(orderUserDto) }
        verify(exactly = 0) { orderRepositoryMock.save(any()) }
    }

    @Test
    fun `should get all orders successfully`() {
        // Given
        every { orderRepositoryMock.findAll() } returns listOf(orderUserDto.toEntity())

        // When
        val result = orderService.getOrders()

        // Then
        assertEquals(listOf(orderUserDto), result)
        verify(exactly = 1) { orderRepositoryMock.findAll() }
    }

    @Test
    fun `should get all products associated to an order successfully`() {
        // Given
        every { orderDetailRepositoryMock.findAllByOrder_IdOrder(any()) } returns Optional.empty()

        // When
        val result = orderService.getProductsByOrderId(UUID.randomUUID())

        // Then
        assertEquals(emptyList(), result)
        verify(exactly = 1) { orderDetailRepositoryMock.findAllByOrder_IdOrder(any()) }
    }
}
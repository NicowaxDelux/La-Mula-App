package com.lamulaapp.service

import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.datamock.*
import com.lamulaapp.exception.EntityNotFoundException
import com.lamulaapp.repository.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*
import kotlin.test.assertEquals

class CheckoutServiceTest {

    private val userRepositoryMock = mockk<UserRepository>()
    private val companyRepositoryMock = mockk<CompanyRepository>()
    private val orderRepositoryMock = mockk<OrderRepository>()
    private val orderDetailRepositoryMock = mockk<OrderDetailRepository>()
    private val orderStatusRepositoryMock = mockk<OrderStatusRepository>()
    private val productRepositoryMock = mockk<ProductRepository>()
    private val checkoutService = CheckoutService(
        userRepository = userRepositoryMock,
        companyRepository = companyRepositoryMock,
        orderRepository = orderRepositoryMock,
        orderDetailRepository = orderDetailRepositoryMock,
        orderStatusRepository = orderStatusRepositoryMock,
        productRepository = productRepositoryMock
    )

    companion object {
        const val ORDER_STATUS_IN_PROGRESS = "IN_PROGRESS"
    }

    @Test
    fun `should execute a user checkout successfully`() {
        // Given
        every { orderStatusRepositoryMock.findByTypeStatus(ORDER_STATUS_IN_PROGRESS) } returns Optional.of(getOrderStatus())
        every { userRepositoryMock.findById(any()) } returns Optional.of(getUserDto().toEntity())
        every { orderRepositoryMock.saveAndFlush(any()) } returns getOrderWithUserDto().toEntity()
        every { orderDetailRepositoryMock.save(any()) } returns getOrderDetailDto().toEntity()
        every { productRepositoryMock.save(any()) } returns getProduct1().toEntity()

        //When
        val result = checkoutService.checkout(getCheckoutRequestUserDto())

        //Then
        assertEquals(checkoutResponseDto(), result)
        verify(exactly = 1) { orderStatusRepositoryMock.findByTypeStatus(ORDER_STATUS_IN_PROGRESS) }
        verify(exactly = 1) { userRepositoryMock.findById(any()) }
        verify(exactly = 1) { orderRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 1) { orderDetailRepositoryMock.save(any()) }
        verify(exactly = 1) { productRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws an EntityNotFoundException when a user tried to checkout successfully`() {
        // Given
        every { orderStatusRepositoryMock.findByTypeStatus(ORDER_STATUS_IN_PROGRESS) } returns Optional.of(getOrderStatus())
        every { userRepositoryMock.findById(any()) } returns Optional.empty()

        //When - Then
        assertThrows<EntityNotFoundException> { checkoutService.checkout(getCheckoutRequestUserDto()) }
        verify(exactly = 1) { orderStatusRepositoryMock.findByTypeStatus(ORDER_STATUS_IN_PROGRESS) }
        verify(exactly = 1) { userRepositoryMock.findById(any()) }
        verify(exactly = 0) { orderRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 0) { orderDetailRepositoryMock.save(any()) }
        verify(exactly = 0) { productRepositoryMock.save(any()) }
    }
}
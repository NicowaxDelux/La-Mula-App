package com.lamulaapp.service

import com.lamulaapp.controller.dto.OrderDto
import com.lamulaapp.repository.OrderRepository
import com.lamulaapp.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class UserServiceTest {

    private val userRepositoryMock = mockk<UserRepository>()
    private val orderRepositoryMock = mockk<OrderRepository>()
    private val userService = UserService(userRepositoryMock, orderRepositoryMock)

    @Test
    fun `should get all orders by user id successfully`() {
        // Given
        every { orderRepositoryMock.findAllByUser_IdUser(any(), any()) } returns Optional.of(emptyList())

        // When
        val result = userService.findOrdersByUserId(UUID.randomUUID(), 1)

        // Then
        assertEquals(emptyList<OrderDto>().size, result.size)
        verify(exactly = 1) { orderRepositoryMock.findAllByUser_IdUser(any(), any()) }
    }
}
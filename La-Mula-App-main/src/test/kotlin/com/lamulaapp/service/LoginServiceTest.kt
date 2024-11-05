package com.lamulaapp.service

import com.lamulaapp.controller.dto.LoginDto
import com.lamulaapp.controller.dto.LoginResponseDto
import com.lamulaapp.controller.dto.RoleDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.LoginNotFoundException
import com.lamulaapp.repository.LoginRepository
import io.mockk.*
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertEquals

class LoginServiceTest {

    private val loginRepositoryMock = mockk<LoginRepository>()
    private val loginService = LoginService(loginRepositoryMock)

    companion object {
        val roleDto = RoleDto(
            idRole = UUID.randomUUID(),
            name = "SELLER",
            description = "Role for sellers",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val idLogin: UUID = UUID.randomUUID()
        val loginDto = LoginDto(
            idLogin = idLogin,
            roleDto = roleDto,
            username = "example@test.com",
            password = "123456",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val loginResponseDto = mockk<LoginResponseDto> {
            every { idEntity } returns UUID.randomUUID()
            every { name } returns "John Doe"
            every { username } returns loginDto.username!!
            every { roleName } returns "CLIENT"
        }
    }

    @Test
    fun `should login successfully`() {
        // Given
        every { loginRepositoryMock.findLoginResponseByUsernameAndPassword(any(), any()) } returns Optional.of(loginResponseDto)

        // When
        val result = loginService.login(loginDto)

        // Then
        assertEquals(loginResponseDto, result)
        verify(exactly = 1) { loginRepositoryMock.findLoginResponseByUsernameAndPassword(any(), any()) }
    }

    @Test
    fun `should throws a LoginNotFoundException when username or password do not match`() {
        // Given
        every { loginRepositoryMock.findLoginResponseByUsernameAndPassword(any(), any()) } returns Optional.empty()

        // When - Then
        assertThrows<LoginNotFoundException> { loginService.login(loginDto) }
        verify(exactly = 1) { loginRepositoryMock.findLoginResponseByUsernameAndPassword(any(), any()) }
    }

    @Test
    fun `should create a new login`() {
        // Given
        every { loginRepositoryMock.findById(idLogin) } returns Optional.empty()
        every { loginRepositoryMock.saveAndFlush(any()) } returns loginDto.toEntity()

        // When
        val result = loginService.createLogin(loginDto)

        // Then
        assertEquals(loginDto, result)
        verify(exactly = 1) { loginRepositoryMock.findById(idLogin) }
        verify(exactly = 1) { loginRepositoryMock.saveAndFlush(any()) }
    }

    @Test
    fun `should throws a DuplicateKeyException when creating a new login`() {
        // Given
        every { loginRepositoryMock.findById(idLogin) } returns Optional.of(loginDto.toEntity())

        // When - Then
        assertThrows<DuplicateKeyException> {
            loginService.createLogin(loginDto)
        }
        verify(exactly = 0) { loginRepositoryMock.save(any()) }
    }

    @Test
    fun `should get all logins`() {
        // Given
        val loginsDto = listOf(loginDto)
        every { loginRepositoryMock.findAll() } returns listOf(loginDto.toEntity())

        // When
        val result = loginService.getLogins()

        // Then
        assertEquals(loginsDto.size, result.size)
    }

    @Test
    fun `should get login by id`() {
        // Given
        every { loginRepositoryMock.findById(idLogin) } returns Optional.of(loginDto.toEntity())

        // When
        val result = loginService.getLoginById(idLogin)

        // Then
        assertEquals(loginDto, result)
        verify(exactly = 1) { loginRepositoryMock.findById(idLogin) }
    }

    @Test
    fun `should throws an EntityNotFoundException when getting login by id`() {
        // Given
        every { loginRepositoryMock.findById(idLogin) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { loginService.getLoginById(idLogin) }
        verify(exactly = 1) { loginRepositoryMock.findById(any()) }
    }

    @Test
    fun `should update a password`() {
        // Given
        val newPassword = "newPassword"
        every { loginRepositoryMock.findById(idLogin) } returns Optional.of(loginDto.toEntity())
        every { loginRepositoryMock.save(any()) } returns loginDto.toEntity().copy(password = newPassword)

        // When
        val result = loginService.updatePassword(idLogin, loginDto.copy(password = newPassword))

        // Then
        assertEquals(newPassword, result.password)
        verify(exactly = 1) { loginRepositoryMock.findById(idLogin) }
        verify(exactly = 1) { loginRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws an EntityNotFoundException when updating password`() {
        // Given
        every { loginRepositoryMock.findById(idLogin) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { loginService.updatePassword(idLogin, loginDto) }
        verify(exactly = 1) { loginRepositoryMock.findById(idLogin) }
        verify(exactly = 0) { loginRepositoryMock.save(any()) }
    }

    @Test
    fun `should delete a login`() {
        // Given
        every { loginRepositoryMock.findById(idLogin) } returns Optional.of(loginDto.toEntity())
        every { loginRepositoryMock.deleteById(idLogin) } just Runs

        // When
        loginService.deleteLogin(idLogin)

        // Then
        verify(exactly = 1) { loginRepositoryMock.findById(idLogin) }
        verify(exactly = 1) { loginRepositoryMock.deleteById(idLogin) }
    }

    @Test
    fun `should throws an EntityNotFoundException when deleting login`() {
        // Given
        every { loginRepositoryMock.findById(idLogin) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { loginService.deleteLogin(idLogin) }
        verify(exactly = 1) { loginRepositoryMock.findById(idLogin) }
        verify(exactly = 0) { loginRepositoryMock.deleteById(idLogin) }
    }
}
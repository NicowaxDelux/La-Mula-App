package com.lamulaapp.service

import com.lamulaapp.controller.dto.CompanyDto
import com.lamulaapp.controller.dto.LoginDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import com.lamulaapp.repository.CompanyRepository
import com.lamulaapp.service.LoginServiceTest.Companion.roleDto
import io.mockk.*
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertEquals

class CompanyServiceTest {

    private val companyRepositoryMock = mockk<CompanyRepository>()
    private val companyService = CompanyService(companyRepositoryMock)

    companion object {
        val loginDto = LoginDto(
            idLogin = UUID.randomUUID(),
            roleDto = roleDto,
            username = "plural.coffee@example.com",
            password = "123456",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val idCompany: UUID = UUID.randomUUID()
        val companyDto = CompanyDto(
            idCompany,
            loginDto,
            name = "Plural Coffee",
            email = "plural.coffee@example.com",
            address = "Calle 123 Siempre Viva",
            phone = "(+57) 123-212-321",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )
    }

    @Test
    fun `should create a new company`() {
        // Given
        every { companyRepositoryMock.findById(idCompany) } returns Optional.empty()
        every { companyRepositoryMock.save(any()) } returns companyDto.toEntity()

        // When
        val result = companyService.createCompany(companyDto)

        // Then
        assertEquals(companyDto, result)
        verify(exactly = 1) { companyRepositoryMock.findById(idCompany) }
        verify(exactly = 1) { companyRepositoryMock.save(companyDto.toEntity()) }
    }

    @Test
    fun `should throws DuplicateKeyException when creating a new company`() {
        // Given
        every { companyRepositoryMock.findById(idCompany) } returns Optional.of(companyDto.toEntity())

        // When - Then
        assertThrows<DuplicateKeyException> { companyService.createCompany(companyDto) }
        verify(exactly = 1) { companyRepositoryMock.findById(idCompany) }
        verify(exactly = 0) { companyRepositoryMock.save(companyDto.toEntity()) }
    }

    @Test
    fun `should get all companies`() {
        // Given
        every { companyRepositoryMock.findAll() } returns listOf(companyDto.toEntity())

        // When
        val result = companyService.getCompanies()

        // Then
        assertEquals(listOf(companyDto), result)
        verify(exactly = 1) { companyRepositoryMock.findAll() }
    }

    @Test
    fun `should get a company by id`() {
        // Given
        every { companyRepositoryMock.findById(idCompany) } returns Optional.of(companyDto.toEntity())

        // When
        val result = companyService.getCompanyById(idCompany)

        // Then
        assertEquals(companyDto, result)
        verify(exactly = 1) { companyRepositoryMock.findById(idCompany) }
    }

    @Test
    fun `should throw exception when getting a company by id`() {
        // Given
        every { companyRepositoryMock.findById(idCompany) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { companyService.getCompanyById(idCompany) }
        verify(exactly = 1) { companyRepositoryMock.findById(idCompany) }
    }

    @Test
    fun `should update a company`() {
        // Given
        every { companyRepositoryMock.findById(idCompany) } returns Optional.of(companyDto.toEntity())
        every { companyRepositoryMock.save(any()) } returns companyDto.toEntity()

        // When
        val result = companyService.updateCompany(idCompany, companyDto)

        // Then
        assertEquals(companyDto, result)
        verify(exactly = 1) { companyRepositoryMock.findById(idCompany) }
        verify(exactly = 1) { companyRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws an EntityNotFoundException when updating a company`() {
        // Given
        every { companyRepositoryMock.findById(idCompany) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { companyService.updateCompany(idCompany, companyDto) }
        verify(exactly = 1) { companyRepositoryMock.findById(idCompany) }
        verify(exactly = 0) { companyRepositoryMock.save(companyDto.toEntity()) }
    }

    @Test
    fun `should throws an KeysAreDifferentException when updating a company`() {
        // Given
        every { companyRepositoryMock.findById(any()) } returns Optional.of(companyDto.toEntity())

        // When - Then
        assertThrows<KeysAreDifferentException> { companyService.updateCompany(UUID.randomUUID(), companyDto) }
        verify(exactly = 1) { companyRepositoryMock.findById(any()) }
        verify(exactly = 0) { companyRepositoryMock.save(any()) }
    }

    @Test
    fun `should delete a company`() {
        // Given
        every { companyRepositoryMock.findById(idCompany) } returns Optional.of(companyDto.toEntity())
        every { companyRepositoryMock.deleteById(idCompany) } just Runs

        // When
        companyService.deleteCompany(idCompany)

        // Then
        verify(exactly = 1) { companyRepositoryMock.findById(idCompany) }
        verify(exactly = 1) { companyRepositoryMock.deleteById(idCompany) }
    }

    @Test
    fun `should throws an EntityNotFoundException when deleting a company`() {
        // Given
        every { companyRepositoryMock.findById(idCompany) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { companyService.deleteCompany(idCompany) }
        verify(exactly = 1) { companyRepositoryMock.findById(idCompany) }
        verify(exactly = 0) { companyRepositoryMock.delete(companyDto.toEntity()) }
    }
}
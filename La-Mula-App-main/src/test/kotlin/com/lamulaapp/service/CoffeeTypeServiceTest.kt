package com.lamulaapp.service

import com.lamulaapp.controller.dto.CoffeeTypeDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import com.lamulaapp.repository.CoffeeTypeRepository
import io.mockk.*
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertEquals

class CoffeeTypeServiceTest {

    private val coffeeTypeRepositoryMock = mockk<CoffeeTypeRepository>()
    private val coffeeTypeService = CoffeeTypeService(coffeeTypeRepositoryMock)

    companion object {
        val idCoffeeType: UUID = UUID.randomUUID()
        val coffeeTypeDto = CoffeeTypeDto(
            idCoffeeType = idCoffeeType,
            name = "Coffee Type name",
            description = "Coffee Type description",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )
    }

    @Test
    fun `should create a coffee type`() {
        // Given
        every { coffeeTypeRepositoryMock.findById(idCoffeeType) } returns Optional.empty()
        every { coffeeTypeRepositoryMock.findByName(any()) } returns Optional.empty()
        every { coffeeTypeRepositoryMock.save(any()) } returns coffeeTypeDto.toEntity()

        // When
        val result = coffeeTypeService.createCoffeeType(coffeeTypeDto)

        // Then
        assertEquals(coffeeTypeDto, result)
        verify(exactly = 1) { coffeeTypeRepositoryMock.findById(idCoffeeType) }
        verify(exactly = 1) { coffeeTypeRepositoryMock.findByName(any()) }
        verify(exactly = 1) { coffeeTypeRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws a DuplicateKeyException when creating a coffee type`() {
        // Given
        every { coffeeTypeRepositoryMock.findById(idCoffeeType) } returns Optional.of(coffeeTypeDto.toEntity())

        // When - Then
        assertThrows<DuplicateKeyException> { coffeeTypeService.createCoffeeType(coffeeTypeDto) }
        verify(exactly = 1) { coffeeTypeRepositoryMock.findById(idCoffeeType) }
        verify(exactly = 0) { coffeeTypeRepositoryMock.findByName(any()) }
        verify(exactly = 0) { coffeeTypeRepositoryMock.save(any()) }
    }

    @Test
    fun `should get all coffee types`() {
        // Given
        val coffeeTypesDto = listOf(coffeeTypeDto)
        every { coffeeTypeRepositoryMock.findAll() } returns listOf(coffeeTypeDto.toEntity())

        // When
        val result = coffeeTypeService.getCoffeeTypes()

        // Then
        assertEquals(coffeeTypesDto.size, result.size)
        verify(exactly = 1) { coffeeTypeRepositoryMock.findAll() }
    }

    @Test
    fun `should get a coffee type by ID`() {
        // Given
        every { coffeeTypeRepositoryMock.findById(idCoffeeType) } returns Optional.of(coffeeTypeDto.toEntity())

        // When
        val result = coffeeTypeService.getCoffeeTypeById(idCoffeeType)

        // Then
        assertEquals(coffeeTypeDto, result)
        verify(exactly = 1) { coffeeTypeRepositoryMock.findById(idCoffeeType) }
    }

    @Test
    fun `should throws an EntityNotFoundException when creating a coffee type`() {
        // Given
        every { coffeeTypeRepositoryMock.findById(idCoffeeType) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { coffeeTypeService.getCoffeeTypeById(idCoffeeType) }
        verify(exactly = 1) { coffeeTypeRepositoryMock.findById(idCoffeeType) }
    }

    @Test
    fun `should update a coffee type`() {
        // Given
        val updatedCoffeeTypeDto = coffeeTypeDto.copy(
            name = "UPDATED COFFEE TYPE NAME",
            description = "UPDATED COFFEE TYPE DESCRIPTION",
            updatedAt = LocalDateTime.now(),
            updatedBy = "SYSTEM"
        )

        every { coffeeTypeRepositoryMock.findById(any()) } returns Optional.of(updatedCoffeeTypeDto.toEntity())
        every { coffeeTypeRepositoryMock.save(any()) } returns updatedCoffeeTypeDto.toEntity()

        // When
        val result = coffeeTypeService.updateCoffeeType(idCoffeeType, updatedCoffeeTypeDto)

        // Then
        assertEquals(updatedCoffeeTypeDto, result)
        verify(exactly = 1) { coffeeTypeRepositoryMock.findById(any()) }
        verify(exactly = 1) { coffeeTypeRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws and EntityNotFoundException when updating a coffee type`() {
        // Given
        every { coffeeTypeRepositoryMock.findById(idCoffeeType) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { coffeeTypeService.updateCoffeeType(idCoffeeType, coffeeTypeDto) }
        verify(exactly = 1) { coffeeTypeRepositoryMock.findById(idCoffeeType) }
        verify(exactly = 0) { coffeeTypeRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws a KeysAreDifferentException when updating a coffee type`() {
        // Given
        val newID = UUID.randomUUID()
        every { coffeeTypeRepositoryMock.findById(newID) } returns Optional.of(coffeeTypeDto.toEntity())

        // When - Then
        assertThrows<KeysAreDifferentException> { coffeeTypeService.updateCoffeeType(newID, coffeeTypeDto) }
        verify(exactly = 1) { coffeeTypeRepositoryMock.findById(newID) }
        verify(exactly = 0) { coffeeTypeRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws an IllegalArgumentException when updating a coffee type`() {
        // Given
        every { coffeeTypeRepositoryMock.findById(idCoffeeType) } returns Optional.of(coffeeTypeDto.toEntity())

        // When - Then
        assertThrows<IllegalArgumentException> { coffeeTypeService.updateCoffeeType(idCoffeeType, coffeeTypeDto) }
        verify(exactly = 1) { coffeeTypeRepositoryMock.findById(idCoffeeType) }
        verify(exactly = 0) { coffeeTypeRepositoryMock.save(any()) }
    }

    @Test
    fun `should delete a coffee type`() {
        // Given
        every { coffeeTypeRepositoryMock.findById(idCoffeeType) } returns Optional.of(coffeeTypeDto.toEntity())
        every { coffeeTypeRepositoryMock.deleteById(idCoffeeType) } just Runs

        // When
        coffeeTypeService.deleteCoffeeType(idCoffeeType)

        // Then
        verify(exactly = 1) { coffeeTypeRepositoryMock.findById(idCoffeeType) }
        verify(exactly = 1) { coffeeTypeRepositoryMock.deleteById(idCoffeeType) }
    }

    @Test
    fun `should throws an EntityNotFoundException when deleting a coffee type`() {
        // Given
        every { coffeeTypeRepositoryMock.findById(idCoffeeType) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { coffeeTypeService.deleteCoffeeType(idCoffeeType) }
        verify(exactly = 0) { coffeeTypeRepositoryMock.deleteById(idCoffeeType) }
    }
}
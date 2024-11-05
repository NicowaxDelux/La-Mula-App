package com.lamulaapp.service

import com.lamulaapp.controller.dto.RoleDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import com.lamulaapp.repository.RoleRepository
import io.mockk.*
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*

class RoleServiceTest {

    private val roleRepositoryMock = mockk<RoleRepository>()
    private val roleService = RoleService(roleRepositoryMock)

    companion object {
        val idRole: UUID = UUID.randomUUID()
        val roleDto = RoleDto(
            idRole = idRole,
            name = "SELLER",
            description = "Role for sellers",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )
    }

    @Test
    fun `should create new role`() {
        // Given
        every { roleRepositoryMock.findById(idRole) } returns Optional.empty()
        every { roleRepositoryMock.findByName(any()) } returns Optional.empty()
        every { roleRepositoryMock.save(any()) } returns roleDto.toEntity()

        // When
        val result = roleService.createRole(roleDto)

        // Then
        assertEquals(roleDto, result)
        verify(exactly = 1) { roleRepositoryMock.findById(any()) }
        verify(exactly = 1) { roleRepositoryMock.findByName(any()) }
        verify(exactly = 1) { roleRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws a DuplicateKeyException when creating new role`() {
        // Given
        every { roleRepositoryMock.findById(idRole) } returns Optional.of(roleDto.toEntity())

        // When - Then
        assertThrows<DuplicateKeyException> { roleService.createRole(roleDto) }
        verify(exactly = 0) { roleRepositoryMock.findByName(any()) }
        verify(exactly = 0) { roleRepositoryMock.save(any()) }
    }

    @Test
    fun `should get all roles`() {
        // Given
        val rolesDto = listOf(roleDto)
        every { roleRepositoryMock.findAll() } returns listOf(roleDto.toEntity())

        // When
        val result = roleService.getRoles()

        // Then
        assertEquals(rolesDto.size, result.size)
        verify(exactly = 1) { roleRepositoryMock.findAll() }
    }

    @Test
    fun `should get a role by ID`() {
        // Given
        every { roleRepositoryMock.findById(idRole) } returns Optional.of(roleDto.toEntity())

        // When
        val result = roleService.getRoleById(idRole)

        // Then
        assertEquals(roleDto, result)
    }

    @Test
    fun `should throws an EntityNotFoundException when getting the role by ID`() {
        // Given
        every { roleRepositoryMock.findById(idRole) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { roleService.getRoleById(idRole) }
        verify(exactly = 1) { roleRepositoryMock.findById(any()) }
    }

    @Test
    fun `should update a role`() {
        // Given
        val updatedRoleDto = roleDto.copy(
            name = "UPDATED_SELLER",
            updatedAt = LocalDateTime.now(),
            updatedBy = "SYSTEM"
        )

        every { roleRepositoryMock.findById(idRole) } returns Optional.of(roleDto.toEntity())
        every { roleRepositoryMock.save(any()) } returns updatedRoleDto.toEntity()

        // When
        val result = roleService.updateRole(idRole, updatedRoleDto)

        // Then
        assertEquals(updatedRoleDto, result)
        verify(exactly = 1) { roleRepositoryMock.findById(idRole) }
        verify(exactly = 1) { roleRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws an EntityNotFoundException when updating the role`() {
        // Given
        every { roleRepositoryMock.findById(idRole) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { roleService.updateRole(idRole, roleDto) }
        verify(exactly = 1) { roleRepositoryMock.findById(idRole) }
        verify(exactly = 0) { roleRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws a KeysAreDifferentException when updating the role`() {
        // Given
        val newID = UUID.randomUUID()
        every { roleRepositoryMock.findById(idRole) } returns Optional.of(
            roleDto.toEntity()
                .copy(idRole = newID)
        )

        // When - Then
        assertThrows<KeysAreDifferentException> { roleService.updateRole(idRole, roleDto.copy(idRole = newID)) }
        verify(exactly = 1) { roleRepositoryMock.findById(idRole) }
        verify(exactly = 0) { roleRepositoryMock.save(any()) }
    }

    @Test
    fun `should throws an IllegalArgumentException when updating the role`() {
        // Given
        every { roleRepositoryMock.findById(idRole) } returns Optional.of(roleDto.toEntity())

        // When - Then
        assertThrows<IllegalArgumentException> { roleService.updateRole(idRole, roleDto) }
        verify(exactly = 1) { roleRepositoryMock.findById(idRole) }
        verify(exactly = 0) { roleRepositoryMock.save(any()) }
    }

    @Test
    fun `should delete a role by ID`() {
        // Given
        every { roleRepositoryMock.findById(idRole) } returns Optional.of(roleDto.toEntity())
        every { roleRepositoryMock.deleteById(idRole) } just Runs

        // When
        roleService.deleteRole(idRole)

        // Then
        verify(exactly = 1) { roleRepositoryMock.findById(idRole) }
        verify(exactly = 1) { roleRepositoryMock.deleteById(idRole) }
    }

    @Test
    fun `should throws an EntityNotFoundException when deleting the role`() {
        // Given
        every { roleRepositoryMock.findById(idRole) } returns Optional.empty()

        // When - Then
        assertThrows<EntityNotFoundException> { roleService.deleteRole(idRole) }
        verify(exactly = 0) { roleRepositoryMock.deleteById(idRole) }
    }
}
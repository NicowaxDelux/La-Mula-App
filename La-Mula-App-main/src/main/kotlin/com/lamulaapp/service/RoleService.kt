package com.lamulaapp.service

import com.lamulaapp.repository.RoleRepository
import com.lamulaapp.controller.dto.RoleDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class RoleService(
    private val roleRepository: RoleRepository
) {
    fun createRole(roleDto: RoleDto): RoleDto{
        val foundById = roleDto.idRole?.let { roleRepository.findById(it) }

        if (foundById != null && foundById.isPresent){
            throw DuplicateKeyException("This ID already exists in database!.")
        }

        val foundByName = roleRepository.findByName(roleDto.name)

        if (foundByName.isPresent){
            throw DuplicateKeyException("The name: ${roleDto.name} already exists in database.")
        }

        val response = roleRepository.save(roleDto.toEntity())
        return response.toDto()
    }

    fun getRoles(): List<RoleDto> {
        return roleRepository.findAll().map { it.toDto() }
    }

    fun getRoleById(id: UUID): RoleDto? {
        val response = roleRepository.findById(id)

        if (!response.isPresent){
            throw EntityNotFoundException("Sorry, this ID doesn't exist.")
        }
        return response.get().toDto()
    }

    fun updateRole(id: UUID, roleDto: RoleDto): RoleDto?{
        val response = roleRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("This ID doesn't exist,please try again.")
        }

        if (id != roleDto.idRole) {
            throw KeysAreDifferentException("This ID doesn't exist, please try again.")
        }

        if (roleDto.updatedBy == null){
            throw IllegalArgumentException("The field 'updateBy' is mandatory!.")
        }

        val update = roleDto
            .toEntity()
            .copy(updatedAt = LocalDateTime.now(), updatedBy = roleDto.updatedBy)

        return roleRepository.save(update).toDto()
    }

    fun deleteRole(id: UUID) {

        val response = roleRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("This ID doesn't exist,please try again.")
        }

        roleRepository.deleteById(id)
    }
}
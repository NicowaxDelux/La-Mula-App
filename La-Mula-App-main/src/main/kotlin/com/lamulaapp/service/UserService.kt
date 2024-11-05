package com.lamulaapp.service

import com.lamulaapp.controller.dto.UserDto
import com.lamulaapp.repository.UserRepository
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
){
    fun createUser(userDto: UserDto): UserDto {
        val foundById = userDto.idUser?.let { userRepository.findById(it) }

        if (foundById != null && foundById.isPresent){
            throw DuplicateKeyException("This ID already exists in database!.")
        }

        val response = userRepository.saveAndFlush(userDto.toEntity())
        return response.toDto()
    }

    fun getUsers(): List<UserDto> {
        return userRepository.findAll().map { it.toDto() }
    }

    fun getUserById(id: UUID): UserDto? {
        val response = userRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("Sorry, this ID doesn't exist.")
        }
        return response.get().toDto()
    }

    fun updateUser(id: UUID, userDto: UserDto): UserDto? {
        val response = userRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("This ID doesn't exist,please try again.")
        }

        if (id != userDto.idUser) {
            throw KeysAreDifferentException("This ID doesn't exist, please try again.")
        }

        if (userDto.updatedBy == null) {
            throw IllegalArgumentException("The field 'updatedBy' is mandatory!.")
        }

        val updateDate = userDto
            .toEntity()
            .copy(updatedAt = LocalDateTime.now(), updatedBy = userDto.updatedBy)

        return userRepository.save(updateDate).toDto()
    }

    fun deleteUser(id: UUID) {
        val response = userRepository.findById(id)

        if (!response.isPresent)
            throw EntityNotFoundException("This ID doesn't exist,please try again.")

        userRepository.deleteById(id)
    }
}
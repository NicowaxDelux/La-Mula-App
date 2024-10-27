package com.lamulaapp.service

import com.lamulaapp.controller.dto.UserDto
import com.lamulaapp.repository.UserRepository
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
){
    fun createUser(userDto: UserDto): UserDto {
        val response = userRepository.save(userDto.toEntity())
        return response.toDto()
    }

    fun getUsers(): List<UserDto> {
        return userRepository.findAll().map { it.toDto() }
    }

    fun getUserById(id: UUID): UserDto? {
        val response = userRepository.findById(id)

        if (!response.isPresent) {
            return null
        }
        return response.get().toDto()
    }

    fun updateUser(id: UUID, userDto: UserDto): UserDto? {
        val response = userRepository.findById(id)

        if (!response.isPresent) {
            return null
        }

        if (id != userDto.idUser) {
            return null
        }

        return userRepository.save(userDto.toEntity()).toDto()
    }

    fun deleteUser(id: UUID) {
        userRepository.deleteById(id)
    }
}
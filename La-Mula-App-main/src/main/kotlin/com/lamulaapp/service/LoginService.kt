package com.lamulaapp.service

import com.lamulaapp.controller.dto.LoginDto
import com.lamulaapp.controller.dto.LoginResponseDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.LoginNotFoundException
import com.lamulaapp.repository.LoginRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class LoginService(
    private val loginRepository: LoginRepository,
) {

    fun login(loginDto: LoginDto): LoginResponseDto {
        val response = loginRepository.findLoginResponseByUsernameAndPassword(
            loginDto.username!!,
            loginDto.password!!
        )

        if (!response.isPresent) {
            throw LoginNotFoundException("Wrong username or password")
        }

        return response.get()
    }

    fun createLogin(loginDto: LoginDto): LoginDto {
        val responsePKFound = loginDto.idLogin?.let { loginRepository.findById(it) }

        if (responsePKFound != null && responsePKFound.isPresent) {
            throw DuplicateKeyException("This ID already exists for the login to be created!")
        }

        val response = loginRepository.saveAndFlush(loginDto.toEntity())
        return response.toDto()
    }

    fun getLogins(): List<LoginDto> {
        return loginRepository.findAll().map { it.toDto() }
    }

    fun getLoginById(id: UUID): LoginDto {
        val response = loginRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDto()
        } else {
            throw EntityNotFoundException("There is no login with the given ID!")
        }
    }

    fun updatePassword(id: UUID, loginDto: LoginDto): LoginDto {
        val response = loginRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no login with the given ID!")
        }

        val entity = response.get()
            .copy(
                password = loginDto.password!!,
                updatedAt = loginDto.updatedAt,
                updatedBy = loginDto.updatedBy
            )

        return loginRepository.save(entity).toDto()
    }

    fun deleteLogin(id: UUID) {
        val response = loginRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no login with the given ID!")
        }

        loginRepository.deleteById(id)
    }
}
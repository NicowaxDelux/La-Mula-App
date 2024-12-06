package com.lamulaapp.service

import com.lamulaapp.controller.dto.SignUpRequestDto
import com.lamulaapp.domain.Company
import com.lamulaapp.domain.Login
import com.lamulaapp.domain.User
import com.lamulaapp.domain.enums.RolesEnum
import com.lamulaapp.repository.CompanyRepository
import com.lamulaapp.repository.LoginRepository
import com.lamulaapp.repository.UserRepository
import io.mockk.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertEquals

class SignUpServiceTest {

    private val loginRepositoryMock = mockk<LoginRepository>()
    private val companyRepositoryMock = mockk<CompanyRepository>()
    private val userRepositoryMock = mockk<UserRepository>()
    private val signUpService = SignUpService(loginRepositoryMock, companyRepositoryMock, userRepositoryMock)

    companion object {
        val loginUserEntity = Login(
            idLogin = UUID.randomUUID(),
            role = RolesEnum.valueOf("CLIENT"),
            username = "john.doe@example.com",
            password = "123456",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val loginCompanyEntity = Login(
            idLogin = UUID.randomUUID(),
            role = RolesEnum.valueOf("SELLER"),
            username = "plural.coffee@example.com",
            password = "123456",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val userEntity = User(
            idUser = UUID.randomUUID(),
            login = loginUserEntity,
            name = "John",
            email = "john.doe@example.com",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val companyEntity = Company(
            idCompany = UUID.randomUUID(),
            login = loginCompanyEntity,
            name = "Plural Coffee",
            email = "plural.coffee@example.com",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val signUpRequestClientDto = SignUpRequestDto(
            name = "John",
            email = "john.doe@example.com",
            password = "123456",
            roleName = "CLIENT"
        )

        val signUpRequestCompanyDto = SignUpRequestDto(
            name = "Plural Coffee",
            email = "plural.coffee@example.com",
            password = "123456",
            roleName = "SELLER"
        )
    }

    @Test
    fun `should signup a client`() {
        // Given
        every { loginRepositoryMock.saveAndFlush(any()) } returns loginUserEntity
        every { userRepositoryMock.saveAndFlush(any()) } returns userEntity


        // When
        val result = signUpService.singUp(signUpRequestClientDto)

        // Then
        assertEquals(signUpRequestClientDto.name, result.name)
        assertEquals(signUpRequestClientDto.email, result.username)
        verify(exactly = 1) { loginRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 1) { userRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 0) { companyRepositoryMock.saveAndFlush(any()) }
    }

    @Test
    fun `should signup a company`() {
        // Given
        every { loginRepositoryMock.saveAndFlush(any()) } returns loginCompanyEntity
        every { companyRepositoryMock.saveAndFlush(any()) } returns companyEntity


        // When
        val result = signUpService.singUp(signUpRequestCompanyDto)

        // Then
        assertEquals(signUpRequestCompanyDto.name, result.name)
        assertEquals(signUpRequestCompanyDto.email, result.username)
        verify(exactly = 1) { loginRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 0) { userRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 1) { companyRepositoryMock.saveAndFlush(any()) }
    }
}
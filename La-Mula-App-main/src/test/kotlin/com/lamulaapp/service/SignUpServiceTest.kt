package com.lamulaapp.service

import com.lamulaapp.controller.dto.SignUpRequestDto
import com.lamulaapp.domain.Company
import com.lamulaapp.domain.Login
import com.lamulaapp.domain.Role
import com.lamulaapp.domain.User
import com.lamulaapp.repository.CompanyRepository
import com.lamulaapp.repository.LoginRepository
import com.lamulaapp.repository.RoleRepository
import com.lamulaapp.repository.UserRepository
import io.mockk.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertEquals

class SignUpServiceTest {

    private val roleRepositoryMock = mockk<RoleRepository>()
    private val loginRepositoryMock = mockk<LoginRepository>()
    private val companyRepositoryMock = mockk<CompanyRepository>()
    private val userRepositoryMock = mockk<UserRepository>()
    private val signUpService = SignUpService(roleRepositoryMock, loginRepositoryMock, companyRepositoryMock, userRepositoryMock)

    companion object {
        val roleUserEntity = Role(
            idRole = UUID.randomUUID(),
            name = "CLIENT",
            description = "Client description",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val roleCompanyEntity = Role(
            idRole = UUID.randomUUID(),
            name = "SELLER",
            description = "Seller description",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val loginUserEntity = Login(
            idLogin = UUID.randomUUID(),
            role = roleUserEntity,
            username = "john.doe@example.com",
            password = "123456",
            createdAt = LocalDateTime.now(),
            createdBy = "SYSTEM"
        )

        val loginCompanyEntity = Login(
            idLogin = UUID.randomUUID(),
            role = roleCompanyEntity,
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
        every { roleRepositoryMock.findByName(any()) } returns Optional.of(roleUserEntity)
        every { loginRepositoryMock.saveAndFlush(any()) } returns loginUserEntity
        every { userRepositoryMock.saveAndFlush(any()) } returns userEntity


        // When
        val result = signUpService.singUp(signUpRequestClientDto)

        // Then
        assertEquals(signUpRequestClientDto.name, result.name)
        assertEquals(signUpRequestClientDto.email, result.username)
        verify(exactly = 1) { roleRepositoryMock.findByName(any()) }
        verify(exactly = 1) { loginRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 1) { userRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 0) { companyRepositoryMock.saveAndFlush(any()) }
    }

    @Test
    fun `should signup a company`() {
        // Given
        every { roleRepositoryMock.findByName(any()) } returns Optional.of(roleCompanyEntity.copy())
        every { loginRepositoryMock.saveAndFlush(any()) } returns loginCompanyEntity
        every { companyRepositoryMock.saveAndFlush(any()) } returns companyEntity


        // When
        val result = signUpService.singUp(signUpRequestCompanyDto)

        // Then
        assertEquals(signUpRequestCompanyDto.name, result.name)
        assertEquals(signUpRequestCompanyDto.email, result.username)
        verify(exactly = 1) { roleRepositoryMock.findByName(any()) }
        verify(exactly = 1) { loginRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 0) { userRepositoryMock.saveAndFlush(any()) }
        verify(exactly = 1) { companyRepositoryMock.saveAndFlush(any()) }
    }
}
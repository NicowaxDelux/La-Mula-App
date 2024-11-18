package com.lamulaapp.service

import com.lamulaapp.controller.dto.SignUpRequestDto
import com.lamulaapp.controller.dto.SignUpResponseDto
import com.lamulaapp.domain.Company
import com.lamulaapp.domain.Login
import com.lamulaapp.domain.User
import com.lamulaapp.domain.enums.RolesEnum
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.repository.CompanyRepository
import com.lamulaapp.repository.LoginRepository
import com.lamulaapp.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SignUpService(
    private val loginRepository: LoginRepository,
    private val companyRepository: CompanyRepository,
    private  val userRepository: UserRepository
) {

    companion object {
        const val ROLE_CLIENT = "CLIENT"
        const val ROLE_SELLER = "SELLER"
        const val CREATE_BY = "SYSTEM"
    }

    fun singUp(signUpRequestDto: SignUpRequestDto): SignUpResponseDto {
        val loginEntity = Login(
            role = RolesEnum.valueOf(signUpRequestDto.roleName!!),
            username = signUpRequestDto.email!!,
            password = signUpRequestDto.password!!,
            createdAt = LocalDateTime.now(),
            createdBy = CREATE_BY
        )

        try {
            loginRepository.saveAndFlush(loginEntity)
        } catch (e: Exception) {
            throw DuplicateKeyException("Email already exists!")
        }

        lateinit var signUpResponseDto: SignUpResponseDto

        if (signUpRequestDto.roleName == ROLE_CLIENT) {
            val userEntity = User(
                login = loginEntity,
                name = signUpRequestDto.name!!,
                email = signUpRequestDto.email,
                address = signUpRequestDto.address,
                phone = signUpRequestDto.phone,
                createdAt = LocalDateTime.now(),
                createdBy = CREATE_BY
            )
            val userResponse = userRepository.saveAndFlush(userEntity)

            signUpResponseDto = SignUpResponseDto(
                idEntity = userResponse.idUser!!,
                name = userResponse.name,
                username = userResponse.email,
                roleName = ROLE_CLIENT
            )
        }

        if (signUpRequestDto.roleName == ROLE_SELLER) {
            val companyEntity = Company(
                login = loginEntity,
                name = signUpRequestDto.name!!,
                email = signUpRequestDto.email,
                address = signUpRequestDto.address,
                phone = signUpRequestDto.phone,
                createdAt = LocalDateTime.now(),
                createdBy = CREATE_BY
            )

            val companyResponse = companyRepository.saveAndFlush(companyEntity)

            signUpResponseDto = SignUpResponseDto(
                idEntity = companyResponse.idCompany!!,
                name = companyResponse.name,
                username = companyResponse.email,
                roleName = ROLE_SELLER
            )
        }

        return signUpResponseDto
    }
}
package com.lamulaapp.service

import com.lamulaapp.controller.dto.CompanyDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import com.lamulaapp.repository.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {

    fun createCompany(companyDto: CompanyDto): CompanyDto {
        val responsePKFound = companyDto.idCompany?.let { companyRepository.findById(it) }

        if (responsePKFound != null && responsePKFound.isPresent) {
            throw DuplicateKeyException("There is a company with the given ID: $responsePKFound.")
        }

        val response = companyRepository.save(companyDto.toEntity())
        return response.toDto()
    }

    fun getCompanies(): List<CompanyDto> {
        return companyRepository.findAll().map { it.toDto() }
    }

    fun getCompanyById(id: UUID): CompanyDto {
        val response = companyRepository.findById(id)

        return if (response.isPresent) {
            response.get().toDto()
        } else {
            throw EntityNotFoundException("Company with ID: $id not found.")
        }
    }

    fun updateCompany(id: UUID, companyDto: CompanyDto): CompanyDto {
        val response = companyRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no company with the given ID: $id.")
        }

        if (id != companyDto.idCompany) {
            throw KeysAreDifferentException("The keys should be the same as the attachment with the given ID!")
        }

        val entity = companyDto
            .toEntity()
            .copy(updatedAt = LocalDateTime.now(), updatedBy = companyDto.updatedBy)
        return companyRepository.save(entity).toDto()
    }

    fun deleteCompany(id: UUID) {
        val response = companyRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no company with the given ID: $id.")
        }
        companyRepository.deleteById(id)
    }
}
package com.lamulaapp.controller

import com.lamulaapp.controller.dto.CompanyDto
import com.lamulaapp.controller.utils.validateCreateCompany
import com.lamulaapp.controller.utils.validateUpdateCompany
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.CompanyService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CompanyController(
    private val companyService: CompanyService
) {

    @PostMapping("/companies")
    @Operation(
        summary = "Add a new company",
        description = "Adds a new company to the database"
    )
    fun createCompany(@RequestBody companyDto: CompanyDto): ResponseEntity<CompanyDto> {
        val validation = validateCreateCompany(companyDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(companyService.createCompany(companyDto), HttpStatus.CREATED)
    }

    @GetMapping("/companies")
    @Operation(
        summary = "Get all companies",
        description = "Returns a list of companies"
    )
    fun getCompanies(): ResponseEntity<List<CompanyDto>> {
        return ResponseEntity(companyService.getCompanies(), HttpStatus.OK)
    }

    @GetMapping("/companies/{id}")
    @Operation(
        summary = "Get a specific company",
        description = "Returns a specific company by id."
    )
    fun getCompanyById(@PathVariable("id") id: UUID): ResponseEntity<CompanyDto> {
        return ResponseEntity(companyService.getCompanyById(id), HttpStatus.OK)
    }

    @PutMapping("/companies/{id}")
    @Operation(
        summary = "Update a specific company",
        description = "Updates a specific company to the database by ID."
    )
    fun updateCompany(@PathVariable("id") id: UUID, @RequestBody companyDto: CompanyDto): ResponseEntity<CompanyDto> {
        val validation = validateUpdateCompany(companyDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(companyService.updateCompany(id, companyDto), HttpStatus.OK)
    }

    @DeleteMapping("/companies/{id}")
    @Operation(
        summary = "Delete a specific company",
        description = "Deletes a specific company by ID."
    )
    fun deleteCompany(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(companyService.deleteCompany(id), HttpStatus.NO_CONTENT)
    }
}
package com.lamulaapp.controller

import com.lamulaapp.controller.dto.LoginDto
import com.lamulaapp.controller.dto.LoginResponseDto
import com.lamulaapp.controller.utils.validateCreateLogin
import com.lamulaapp.controller.utils.validateLogin
import com.lamulaapp.controller.utils.validateUpdatePassword
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.LoginService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/login")
    @Operation(
        summary = "Register a company or a specific user.",
        description = "Register a user with their respective role."
    )
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<LoginResponseDto> {
        val validateLogin = validateLogin(loginDto)

        if (!validateLogin.isValid) {
            throw ValidationErrorsException(validateLogin.errors)
        }

        return ResponseEntity(loginService.login(loginDto), HttpStatus.OK)
    }

    @PostMapping("/logins")
    @Operation(
        summary = "Register a specific user.",
        description = "Register a user with their respective role."
    )
    fun createLogin(@RequestBody loginDto: LoginDto): ResponseEntity<LoginDto> {
        val validation = validateCreateLogin(loginDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(loginService.createLogin(loginDto), HttpStatus.CREATED)
    }

    @GetMapping("/logins")
    @Operation(
        summary = "Get a all users.",
        description = "List all users in the database."
    )
    fun getLogins(): ResponseEntity<List<LoginDto>> {
        return ResponseEntity(loginService.getLogins(),HttpStatus.OK)
    }

    @GetMapping("/logins/{id}")
    @Operation(
        summary = "Get a specific user by ID.",
        description = "Search the database by ID for a specific user."
    )
    fun getLoginById(@PathVariable("id") id: UUID): ResponseEntity<LoginDto> {
        return ResponseEntity(loginService.getLoginById(id),HttpStatus.OK)
    }

    @PutMapping("/logins/{id}")
    @Operation(
        summary = "Update a specific user by ID.",
        description = "Search for a specific user by id and update it."
    )
    fun updatePassword(@PathVariable("id") id: UUID, @RequestBody loginDto: LoginDto): ResponseEntity<LoginDto> {
        val validation = validateUpdatePassword(loginDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(loginService.updatePassword(id, loginDto), HttpStatus.OK)
    }

    @DeleteMapping("/logins/{id}")
    @Operation(
        summary = "Delete a specific user by ID.",
        description = "Search for a specific user by id and delete it."
    )
    fun deleteLogin(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(loginService.deleteLogin(id), HttpStatus.NO_CONTENT)
    }
}
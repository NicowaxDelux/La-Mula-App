package com.lamulaapp.controller

import com.lamulaapp.controller.dto.LoginDto
import com.lamulaapp.controller.utils.validateCreateLogin
import com.lamulaapp.controller.utils.validateUpdatePassword
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.LoginService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/login")
    fun createLogin(@RequestBody loginDto: LoginDto): ResponseEntity<LoginDto> {
        val validation = validateCreateLogin(loginDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(loginService.createLogin(loginDto), HttpStatus.CREATED)
    }

    @GetMapping("/login")
    fun getLogins(): ResponseEntity<List<LoginDto>> {
        return ResponseEntity(loginService.getLogins(),HttpStatus.OK)
    }

    @GetMapping("/login/{id}")
    fun getLoginById(@PathVariable("id") id: UUID): ResponseEntity<LoginDto> {
        return ResponseEntity(loginService.getLoginById(id),HttpStatus.OK)
    }

    @PutMapping("/login/{id}")
    fun updatePassword(@PathVariable("id") id: UUID, @RequestBody loginDto: LoginDto): ResponseEntity<LoginDto> {
        val validation = validateUpdatePassword(loginDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(loginService.updatePassword(id, loginDto), HttpStatus.OK)
    }

    @DeleteMapping("/login/{id}")
    fun deleteLogin(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(loginService.deleteLogin(id), HttpStatus.NO_CONTENT)
    }
}
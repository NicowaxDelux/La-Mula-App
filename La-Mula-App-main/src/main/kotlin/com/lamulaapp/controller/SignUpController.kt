package com.lamulaapp.controller

import com.lamulaapp.controller.dto.SignUpRequestDto
import com.lamulaapp.controller.dto.SignUpResponseDto
import com.lamulaapp.controller.utils.validateSignUp
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.SignUpService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpController(
    private val signUpService: SignUpService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequestDto: SignUpRequestDto): ResponseEntity<SignUpResponseDto> {
        val validation = validateSignUp(signUpRequestDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }
        return ResponseEntity(signUpService.singUp(signUpRequestDto), HttpStatus.CREATED)
    }
}
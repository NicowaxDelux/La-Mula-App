package com.lamulaapp.controller

import com.lamulaapp.controller.dto.ResponseErrorDto
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import com.lamulaapp.exception.LoginNotFoundException
import com.lamulaapp.exception.ValidationErrorsException
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(
        DuplicateKeyException::class,
        DuplicateKeyException::class,
        EntityNotFoundException::class,
        KeysAreDifferentException::class,
        IllegalArgumentException::class,
        LoginNotFoundException::class
    )
    fun handleException(e: Exception): ResponseEntity<ResponseErrorDto> {
        val response = ResponseErrorDto(
            e.message ?: "Error",
            HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ValidationErrorsException::class)
    fun handleValidationErrorsException(e: ValidationErrorsException): ResponseEntity<ResponseErrorDto> {
        val response = ResponseErrorDto(
            e.message ?: "Request validation error",
            HttpStatus.BAD_REQUEST.value(),
            errors = e.errors.associate { it.dataPath.drop(1) to it.message }
        )
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}

package com.lamulaapp.controller

import com.lamulaapp.controller.dto.ResponseErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ResponseErrorDto> {
        val response = ResponseErrorDto(
            e.message ?: "Error",
            HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}
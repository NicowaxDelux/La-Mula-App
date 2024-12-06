package com.lamulaapp.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/health")
    @Operation(
        summary = "Verifica el estado de la aplicacion",
        description = "Su estado de respuesta debe ser OK"
    )
    fun checkStatus(): ResponseEntity<String> {
        return ResponseEntity("OK", HttpStatus.OK)
    }
}
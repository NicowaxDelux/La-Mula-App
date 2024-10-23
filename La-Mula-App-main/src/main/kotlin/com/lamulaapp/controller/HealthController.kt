package com.lamulaapp.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/health")
    fun checkStatus(): ResponseEntity<String> {
        return ResponseEntity("OK", HttpStatus.OK)
    }
}
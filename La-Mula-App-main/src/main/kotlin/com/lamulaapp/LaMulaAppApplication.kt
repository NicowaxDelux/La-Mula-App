package com.lamulaapp

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition
@SpringBootApplication
class LaMulaAppApplication

fun main(args: Array<String>) {
    runApplication<LaMulaAppApplication>(*args)
}

package com.lamulaapp.controller

import com.lamulaapp.controller.dto.CoffeeTypeDto
import com.lamulaapp.controller.utils.validateCreateCoffeeType
import com.lamulaapp.controller.utils.validateUpdateCoffeeType
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.CoffeeTypeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CoffeeTypeController(
    private val coffeeTypeService: CoffeeTypeService
){
    @PostMapping("/coffee-types")
    @Operation(
        summary = "Adds a new coffee type",
        description = "Allows the creation of a new type of coffee in the database or in the system"
    )
    fun createCoffeeType(@RequestBody coffeeTypeDto: CoffeeTypeDto): ResponseEntity<CoffeeTypeDto> {
        val validation = validateCreateCoffeeType(coffeeTypeDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(coffeeTypeService.createCoffeeType(coffeeTypeDto), HttpStatus.CREATED)
    }

    @GetMapping("/coffee-types")
    @Operation(
        summary = "Gets a coffee type",
        description = "Lists the types of coffee that exist in the database."
    )
    fun getCoffeeTypes(): ResponseEntity<List<CoffeeTypeDto>> {
        return ResponseEntity(coffeeTypeService.getCoffeeTypes(), HttpStatus.OK)
    }

    @GetMapping("/coffee-types/{id}")
    @Operation(
        summary = "Gets a coffee type by ID",
        description = "List of details of a specific coffee type according to its ID"
    )
    fun getCoffeeTypeById(@PathVariable("id") id: UUID): ResponseEntity<CoffeeTypeDto> {
        val coffeeType = coffeeTypeService.getCoffeeTypeById(id)
        return ResponseEntity(coffeeType, HttpStatus.OK)
    }

    @PutMapping("/coffee-types/{id}")
    @Operation(
        summary = "Update a coffee type by ID",
        description = "Searches the database for the type of coffee based on ID and updates it."
    )
    fun updateCoffeeType(
        @PathVariable("id") id: UUID,
        @RequestBody coffeeTypeDto: CoffeeTypeDto
    ): ResponseEntity<CoffeeTypeDto> {
        val validation = validateUpdateCoffeeType(coffeeTypeDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        val coffeeType = coffeeTypeService.updateCoffeeType(id, coffeeTypeDto)
        return ResponseEntity(coffeeType, HttpStatus.OK)
    }

    @DeleteMapping("/coffee-types/{id}")
    @Operation(
        summary = "Delete a coffee type by ID",
        description = "Searches the database for the type of coffee based on ID and deletes it."
    )
    fun deleteCoffeeType(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(coffeeTypeService.deleteCoffeeType(id), HttpStatus.NO_CONTENT)
    }
}
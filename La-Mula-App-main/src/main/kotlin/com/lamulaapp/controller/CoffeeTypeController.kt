package com.lamulaapp.controller

import com.lamulaapp.controller.dto.CoffeeTypeDto
import com.lamulaapp.controller.utils.validateCreateCoffeeType
import com.lamulaapp.controller.utils.validateUpdateCoffeeType
import com.lamulaapp.exception.ValidationErrorsException
import com.lamulaapp.service.CoffeeTypeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CoffeeTypeController(
    private val coffeeTypeService: CoffeeTypeService
){
    @PostMapping("/coffee-types")
    fun createCoffeeType(@RequestBody coffeeTypeDto: CoffeeTypeDto): ResponseEntity<CoffeeTypeDto> {
        val validation = validateCreateCoffeeType(coffeeTypeDto)

        if (!validation.isValid) {
            throw ValidationErrorsException(validation.errors)
        }

        return ResponseEntity(coffeeTypeService.createCoffeeType(coffeeTypeDto), HttpStatus.CREATED)
    }

    @GetMapping("/coffee-types")
    fun getCoffeeTypes(): ResponseEntity<List<CoffeeTypeDto>> {
        return ResponseEntity(coffeeTypeService.getCoffeeTypes(), HttpStatus.OK)
    }

    @GetMapping("/coffee-types/{id}")
    fun getCoffeeTypeById(@PathVariable("id") id: UUID): ResponseEntity<CoffeeTypeDto> {
        val coffeeType = coffeeTypeService.getCoffeeTypeById(id)
        return ResponseEntity(coffeeType, HttpStatus.OK)
    }

    @PutMapping("/coffee-types/{id}")
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
    fun deleteCoffeeType(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(coffeeTypeService.deleteCoffeeType(id), HttpStatus.NO_CONTENT)
    }
}
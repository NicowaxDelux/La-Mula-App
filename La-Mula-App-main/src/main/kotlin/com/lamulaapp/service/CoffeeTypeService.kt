package com.lamulaapp.service

import com.lamulaapp.controller.dto.CoffeeTypeDto
import com.lamulaapp.controller.mapper.toDTO
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.repository.CoffeeTypeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CoffeeTypeService(
    private val coffeeTypeRepository: CoffeeTypeRepository
) {

    fun createCoffeeType(coffeeTypeDto: CoffeeTypeDto): CoffeeTypeDto {
        val response = coffeeTypeRepository.save(coffeeTypeDto.toEntity())
        return response.toDTO()
    }

    fun getCoffeeTypes(): List<CoffeeTypeDto> {
        return coffeeTypeRepository.findAll().map { it.toDTO() }
    }

    fun getCoffeeTypeById(id: UUID): CoffeeTypeDto? {
        val response = coffeeTypeRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDTO()
        } else {
            null
        }
    }

    fun updateCoffeeType(id: UUID, coffeeTypeDto: CoffeeTypeDto): CoffeeTypeDto? {
        val response = coffeeTypeRepository.findById(id)

        if (!response.isPresent) {
            return null
        }

        if (id != coffeeTypeDto.id) {
            return null
        }
        return coffeeTypeRepository.save(coffeeTypeDto.toEntity()).toDTO()
    }

    fun deleteCoffeeType(id: UUID) {
        coffeeTypeRepository.deleteById(id)
    }
}

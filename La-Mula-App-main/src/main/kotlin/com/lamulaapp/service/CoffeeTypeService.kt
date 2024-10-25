package com.lamulaapp.service

import com.lamulaapp.controller.dto.CoffeeTypeDto
import com.lamulaapp.controller.mapper.toDto
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
        return response.toDto()
    }

    fun getCoffeeTypes(): List<CoffeeTypeDto> {
        return coffeeTypeRepository.findAll().map { it.toDto() }
    }

    fun getCoffeeTypeById(id: UUID): CoffeeTypeDto? {
        val response = coffeeTypeRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDto()
        } else {
            null
        }
    }

    fun updateCoffeeType(id: UUID, coffeeTypeDto: CoffeeTypeDto): CoffeeTypeDto? {
        val response = coffeeTypeRepository.findById(id)

        if (!response.isPresent) {
            return null
        }

        if (id != coffeeTypeDto.idCoffeeType) {
            return null
        }
        return coffeeTypeRepository.save(coffeeTypeDto.toEntity()).toDto()
    }

    fun deleteCoffeeType(id: UUID) {
        coffeeTypeRepository.deleteById(id)
    }
}

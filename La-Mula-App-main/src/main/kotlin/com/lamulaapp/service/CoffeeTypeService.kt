package com.lamulaapp.service

import com.lamulaapp.controller.dto.CoffeeTypeDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import com.lamulaapp.repository.CoffeeTypeRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CoffeeTypeService(
    private val coffeeTypeRepository: CoffeeTypeRepository
) {

    fun createCoffeeType(coffeeTypeDto: CoffeeTypeDto): CoffeeTypeDto {
        val responsePKFound = coffeeTypeDto.idCoffeeType?.let { coffeeTypeRepository.findById(it) }

        if (responsePKFound != null && responsePKFound.isPresent) {
            throw DuplicateKeyException("This ID already exists for the coffee-type to be created!")
        }

        val responseNameFound = coffeeTypeRepository.findByName(coffeeTypeDto.name)

        if (responseNameFound.isPresent) {
            throw DuplicateKeyException("The name: ${coffeeTypeDto.name} already exists for the coffee-type to be created!")
        }

        val response = coffeeTypeRepository.save(coffeeTypeDto.toEntity())
        return response.toDto()
    }

    fun getCoffeeTypes(): List<CoffeeTypeDto> {
        return coffeeTypeRepository.findAll().map { it.toDto() }
    }

    fun getCoffeeTypeById(id: UUID): CoffeeTypeDto {
        val response = coffeeTypeRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDto()
        } else {
            throw EntityNotFoundException("There is no coffee-type with the given ID!")
        }
    }

    fun updateCoffeeType(id: UUID, coffeeTypeDto: CoffeeTypeDto): CoffeeTypeDto {
        val response = coffeeTypeRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no coffee-type with the given ID!")
        }

        if (id != coffeeTypeDto.idCoffeeType) {
            throw KeysAreDifferentException("The keys should be the same as the coffee-type with the given ID!")
        }

        if (coffeeTypeDto.updatedBy == null) {
            throw IllegalArgumentException("The field 'updatedBy' is mandatory!")
        }

        val entity = coffeeTypeDto
            .toEntity()
            .copy(updatedAt = LocalDateTime.now(), updatedBy = coffeeTypeDto.updatedBy)

        return coffeeTypeRepository.save(entity).toDto()
    }

    fun deleteCoffeeType(id: UUID) {
        val response = coffeeTypeRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no coffee-type with the given ID!")
        }

        coffeeTypeRepository.deleteById(id)
    }
}

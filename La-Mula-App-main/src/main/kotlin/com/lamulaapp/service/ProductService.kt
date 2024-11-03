package com.lamulaapp.service

import com.lamulaapp.controller.dto.ProductDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import com.lamulaapp.repository.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun createProduct(productDto: ProductDto): ProductDto {
        val responsePKFound = productDto.idProduct?.let { productRepository.findById(it) }

        if (responsePKFound != null && responsePKFound.isPresent) {
            throw DuplicateKeyException("This ID already exists for the product to be created!")
        }
        val response = productRepository.save(productDto.toEntity())
        return response.toDto()
    }

    fun getProducts(): List<ProductDto> {
        return productRepository.findAll().map { it.toDto() }
    }

    fun getProductById(id: UUID): ProductDto? {
        val response = productRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDto()
        } else {
            throw EntityNotFoundException("There is no product with the given ID!")
        }
    }

    fun updateProduct(id : UUID, productDto: ProductDto): ProductDto? {
        val response = productRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no product with the given ID!")
        }

        if (id != productDto.idProduct) {
            throw KeysAreDifferentException("The keys should be the same as the product with the given ID!")
        }

        if (productDto.updatedBy == null) {
            throw IllegalArgumentException("The field 'updatedBy' is mandatory!")
        }

        val entity = productDto
            .toEntity()
            .copy(updatedAt = LocalDateTime.now(), updatedBy = productDto.updatedBy)

        return productRepository.save(entity).toDto()
    }

    fun deleteProduct(id: UUID) {
       val response = productRepository.findById(id)

        if (!response.isPresent) {
            throw EntityNotFoundException("There is no product with the given ID!")
        }
        productRepository.deleteById(id)
    }
}
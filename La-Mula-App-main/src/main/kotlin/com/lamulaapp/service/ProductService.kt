package com.lamulaapp.service

import com.lamulaapp.controller.dto.ProductDto
import com.lamulaapp.controller.mapper.toDTO
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun createProduct(productDto: ProductDto): ProductDto {
        val response = productRepository.save(productDto.toEntity())
        return response.toDTO()
    }

    fun getProducts(): List<ProductDto> {
        return productRepository.findAll().map { it.toDTO() }
    }

    fun getProductById(id: UUID): ProductDto? {
        val response = productRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDTO()
        } else {
            null
        }
    }

    fun updateProduct(id : UUID, productDto: ProductDto): ProductDto? {
        val response = productRepository.findById(id)

        if (!response.isPresent) {
            return null
        }

        if (id != productDto.id) {
            return null
        }
        return productRepository.save(productDto.toEntity()).toDTO()
    }

    fun deleteProduct(id: UUID) {
        productRepository.deleteById(id)
    }
}
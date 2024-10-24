package com.lamulaapp.service

import com.lamulaapp.controller.dto.ProductDetailDto
import com.lamulaapp.controller.mapper.toDTO
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.repository.ProductDetailRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductDetailService(
    private val productDetailRepository: ProductDetailRepository
) {

    fun createProductDetail(productDetailDto: ProductDetailDto): ProductDetailDto {
        val response = productDetailRepository.save(productDetailDto.toEntity())
        return response.toDTO()
    }

    fun getProductDetails(): List<ProductDetailDto> {
        return productDetailRepository.findAll().map { it.toDTO() }
    }

    fun getProductDetailById(id: java.util.UUID): ProductDetailDto? {
        val response = productDetailRepository.findById(id)
        return if (response.isPresent) {
            response.get().toDTO()
        } else {
            null
        }
    }

    fun updateProductDetail(id: java.util.UUID, productDetailDto: ProductDetailDto): ProductDetailDto? {
        val response = productDetailRepository.findById(id)

        if (!response.isPresent) {
            return null
        }

        if (id != productDetailDto.idProductDetail) {
            return null
        }
        return productDetailRepository.save(productDetailDto.toEntity()).toDTO()
    }

    fun deleteProductDetail(id: java.util.UUID) {
        productDetailRepository.deleteById(id)
    }
}
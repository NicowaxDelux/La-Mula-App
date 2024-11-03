package com.lamulaapp.controller

import com.lamulaapp.controller.dto.ProductDto
import com.lamulaapp.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class ProductController (
    private val productService: ProductService
) {
    @PostMapping("/products")
    fun createProduct(@RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
        return ResponseEntity(productService.createProduct(productDto), HttpStatus.CREATED)
    }

    @GetMapping("/products")
    fun getProducts(): ResponseEntity<List<ProductDto>> {
        return ResponseEntity(productService.getProducts(),HttpStatus.OK)
    }

    @GetMapping("/products/{id}")
    fun getProductById(@PathVariable("id") id: UUID): ResponseEntity<ProductDto> {
        val product = productService.getProductById(id)
        return ResponseEntity(product, HttpStatus.OK)
    }

    @PutMapping("/products/{id}")
    fun updateProduct(@PathVariable("id") id: UUID, @RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
        val product = productService.updateProduct(id, productDto)
        return ResponseEntity(product, HttpStatus.OK)
    }

    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(productService.deleteProduct(id), HttpStatus.NO_CONTENT)
    }
}
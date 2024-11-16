package com.lamulaapp.controller

import com.lamulaapp.controller.dto.ProductDto
import com.lamulaapp.service.ProductService
import org.springframework.data.domain.Slice
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

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

    @GetMapping("/products2")
    fun getProductsWithPagination(@RequestParam pageNumber: Int? = 0, @RequestParam pageSize: Int? = 10): ResponseEntity<Slice<ProductDto>> {
        return ResponseEntity(productService.getProductsWithPagination(pageNumber!!, pageSize!!),HttpStatus.OK)
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

    @PostMapping("/products/upload")
    fun uploadProducts(@RequestParam file: MultipartFile): ResponseEntity<String> {
        if (file.isEmpty) {
            return ResponseEntity("File is empty!", HttpStatus.BAD_REQUEST)
        }

        val productsCreatedCount = productService.uploadProducts(file)
        return ResponseEntity("File uploaded: $productsCreatedCount products were created successfully!", HttpStatus.CREATED)
    }
}
package com.lamulaapp.controller

import com.lamulaapp.controller.dto.ProductDto
import com.lamulaapp.service.ProductService
import io.swagger.v3.oas.annotations.Operation
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
    @Operation(
        summary = "Create a new product",
        description = "Create a new product.",
    )
    fun createProduct(@RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
        return ResponseEntity(productService.createProduct(productDto), HttpStatus.CREATED)
    }

    @GetMapping("/products")
    @Operation(
        summary = "Get all products.",
        description = "Searches the database for all products and displays them in a list.",
    )
    fun getProducts(): ResponseEntity<List<ProductDto>> {
        return ResponseEntity(productService.getProducts(),HttpStatus.OK)
    }

    @GetMapping("/products2")
    fun getProductsWithPagination(@RequestParam pageNumber: Int? = 0, @RequestParam pageSize: Int? = 10): ResponseEntity<Slice<ProductDto>> {
        return ResponseEntity(productService.getProductsWithPagination(pageNumber!!, pageSize!!),HttpStatus.OK)
    }

    @GetMapping("/products/{id}")
    @Operation(
        summary = "Get product by id.",
        description = "Searches the database for the product by id.",
    )
    fun getProductById(@PathVariable("id") id: UUID): ResponseEntity<ProductDto> {
        val product = productService.getProductById(id)
        return ResponseEntity(product, HttpStatus.OK)
    }

    @PutMapping("/products/{id}")
    @Operation(
        summary = "Update a product",
        description = "Search the database for the product by id and update it.",
    )
    fun updateProduct(@PathVariable("id") id: UUID, @RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
        val product = productService.updateProduct(id, productDto)
        return ResponseEntity(product, HttpStatus.OK)
    }

    @DeleteMapping("/products/{id}")
    @Operation(
        summary = "Delete a product by id.",
        description = "Search for the product in the database by id and delete it.",
    )
    fun deleteProduct(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        return ResponseEntity(productService.deleteProduct(id), HttpStatus.NO_CONTENT)
    }

    @PostMapping("/products/upload")
    @Operation(
        summary = "Upload a list of products",
        description = "Allows a bulk upload of products via a file containing a structure for organizing the products. The file is processed and the products are created in the database. Returns the number of products successfully created. If the file is empty, an error response is returned."
    )
    fun uploadProducts(@RequestParam file: MultipartFile): ResponseEntity<String> {
        if (file.isEmpty) {
            return ResponseEntity("File is empty!", HttpStatus.BAD_REQUEST)
        }

        val productsCreatedCount = productService.uploadProducts(file)
        return ResponseEntity("File uploaded: $productsCreatedCount products were created successfully!", HttpStatus.CREATED)
    }
}
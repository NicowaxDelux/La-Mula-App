package com.lamulaapp.service

import com.lamulaapp.controller.dto.ProductDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.domain.Product
import com.lamulaapp.exception.DuplicateKeyException
import com.lamulaapp.exception.KeysAreDifferentException
import com.lamulaapp.repository.AttachmentRepository
import com.lamulaapp.repository.CoffeeTypeRepository
import com.lamulaapp.repository.CompanyRepository
import com.lamulaapp.repository.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val coffeeTypeRepository: CoffeeTypeRepository,
    private val attachmentRepository: AttachmentRepository,
    private val companyRepository: CompanyRepository,
) {

    companion object {
        val ID_DEFAULT_ATTACHMENT: UUID = UUID.fromString("1e95833b-3cc6-4c46-9252-8a6a6a808b2b")
        val CREATED_BY: String = "SELLER"
    }
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

    fun getProductsWithPagination(pageNumber: Int, pageSize: Int): Slice<ProductDto> {
        val pageable = PageRequest.of(pageNumber, pageSize)
        return productRepository.findAll(pageable).map { it.toDto() }
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

    fun uploadProducts(file: MultipartFile): Int {
        val workBook = XSSFWorkbook(file.inputStream)
        val sheet = workBook.getSheetAt(0)

        val rows = sheet.iterator()
        rows.next() // Skip the header row

        val productsEntities = mutableListOf<Product>()

        while (rows.hasNext()) {
            val row = rows.next()
            val companyCell = row.getCell(0).stringCellValue.split(":").last()
            val nameCell = row.getCell(1).stringCellValue
            val coffeeTypeCell = row.getCell(2).stringCellValue.split(":").last()
            val descriptionCell = row.getCell(3).stringCellValue
            val unitCostCell = row.getCell(4).numericCellValue.toBigDecimal()
            val stockCell = row.getCell(5).numericCellValue.toInt()
            val packageSizeCell = row.getCell(6).stringCellValue

            val productEntity = Product(
                coffeeType = coffeeTypeRepository.findById(UUID.fromString(coffeeTypeCell)).get(),
                attachment = attachmentRepository.findById(ID_DEFAULT_ATTACHMENT).get(),
                company = companyRepository.findById(UUID.fromString(companyCell)).get(),
                name = nameCell,
                description = descriptionCell,
                unitCost = unitCostCell,
                stock = stockCell,
                packageSize = packageSizeCell,
                createdAt = LocalDateTime.now(),
                createdBy = CREATED_BY
            )

            productsEntities.add(productEntity)
        }

        productRepository.saveAll(productsEntities)
        workBook.close()
        return productsEntities.size
    }
}
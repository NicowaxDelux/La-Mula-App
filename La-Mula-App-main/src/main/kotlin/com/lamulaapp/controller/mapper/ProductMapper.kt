package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.ProductDto
import com.lamulaapp.domain.Product

fun Product.toDto(): ProductDto = ProductDto(
    idProduct = this.idProduct,
    name = this.name,
    description = this.description,
    coffeeTypeDto = this.coffeeType?.toDto(),
    companyDto = this.company.toDto(),
    unitCost = this.unitCost,
    stock = this.stock,
    attachmentDto = this.attachment.toDto(),
    packageSize = this.packageSize,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun ProductDto.toEntity() = Product(
    idProduct = this.idProduct,
    name = this.name!!,
    description = this.description,
    coffeeType = this.coffeeTypeDto?.toEntity(),
    attachment = this.attachmentDto?.toEntity()!!,
    company = this.companyDto?.toEntity()!!,
    unitCost = this.unitCost!!,
    stock = this.stock!!,
    packageSize = this.packageSize!!,
    createdAt = this.createdAt!!,
    createdBy = this.createdBy!!,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)
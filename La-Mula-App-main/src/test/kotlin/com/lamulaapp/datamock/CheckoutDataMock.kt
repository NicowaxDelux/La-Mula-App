package com.lamulaapp.datamock

import com.lamulaapp.controller.dto.*
import com.lamulaapp.domain.OrderStatus
import java.time.LocalDateTime
import java.util.*

fun getOrderStatus() = OrderStatus(
    idOrderStatus = UUID.fromString("0d537ff6-c232-45e4-899a-d8597e105569"),
    typeStatus = "IN_PROGRESS",
    description = "IN_PROGRESS",
    createdAt = LocalDateTime.parse("2024-11-11T20:35:00"),
    createdBy = "SYSTEM"
)

fun getCoffeeTypeDto() = CoffeeTypeDto(
    idCoffeeType = UUID.fromString("1577eec9-c47b-465e-b64a-386720384770"),
    name = "Chocolat",
    description = "Chocolat",
    createdAt = LocalDateTime.parse("2024-11-11T20:35:00"),
    createdBy = "SYSTEM"
)

fun getCompanyDto() = CompanyDto(
    idCompany = UUID.fromString("2d2f15af-d941-4fb0-8df5-85a412791986"),
    loginDto = getLoginDto(),
    name = "Plural Coffee",
    email = "plural@example.com",
    createdAt = LocalDateTime.parse("2024-11-11T20:35:00"),
    createdBy = "SYSTEM"
)

fun getProduct1() = ProductDto(
    idProduct = UUID.fromString("4ef3eb1f-0552-446a-bd26-1437a991152e"),
    name = "Juan Valdez - Chocolate",
    coffeeTypeDto = getCoffeeTypeDto(),
    attachmentDto = getAttachmentDto(),
    companyDto = getCompanyDto(),
    description = "Coffee description",
    unitCost = 10.34.toBigDecimal(),
    packageSize = "10Kg",
    stock = 45,
    createdAt = LocalDateTime.parse("2024-11-11T20:35:00"),
    createdBy = "SYSTEM"
)

fun getProductQuantityDto() = ProductQuantityDto(
    productDto = getProduct1(),
    quantity = 12
)

fun getCheckoutRequestUserDto() = CheckoutRequestDto(
    idEntity = UUID.fromString("fd218381-44e8-4d48-b3af-54cdf31d5dd6"),
    username = "jhon@example.com",
    rolName = "CLIENT",
    productQuantityList = listOf(getProductQuantityDto())
)

fun checkoutResponseDto() = CheckoutResponseDto(
    orderDto = getOrderWithUserDto()
)
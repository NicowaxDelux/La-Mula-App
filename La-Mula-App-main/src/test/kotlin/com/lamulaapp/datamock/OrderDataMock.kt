package com.lamulaapp.datamock

import com.lamulaapp.controller.dto.*
import java.time.LocalDateTime
import java.util.*

fun getOrderStatusInProgress() = OrderStatusDto(
    idOrderStatus = UUID.fromString("7aa3fb3a-1382-44fe-8895-4df890601de4"),
    typeStatus = "IN_PROGRESS",
    description = "Order in progress",
    createdAt = LocalDateTime.parse("2024-11-11T20:35:00"),
    createdBy = "SYSTEM",
)

fun getLoginDto() = LoginDto(
    idLogin = UUID.fromString("1bf8c577-5e2a-4d8d-a785-5dc1984ab51a"),
    role = "CLIENT",
    username = "john.doe@example.com",
    password = "password",
    createdAt = LocalDateTime.parse("2024-11-11T20:35:00"),
    createdBy = "SYSTEM",
)

fun getUserDto() = UserDto(
    idUser = UUID.fromString("e974556e-9c95-4210-9025-ab609591f18c"),
    loginDto = getLoginDto(),
    name = "John Doe",
    email = "john.doe@example.com",
    createdAt = LocalDateTime.parse("2024-11-11T20:35:00"),
    createdBy = "SYSTEM"
)

fun getOrderWithUserDto() = OrderDto(
    idOrder = UUID.fromString("046535fd-0f3e-41e4-ae9a-5284d0376d05"),
    userDto = getUserDto(),
    orderStatusDto = getOrderStatusInProgress(),
    totalCost = 105.00.toBigDecimal(),
    createdAt = LocalDateTime.parse("2024-11-11T20:35:00"),
    createdBy = "SYSTEM"
)

fun getOrderDetailDto() = OrderDetailDto(
    idOrderDetail = UUID.fromString("046535fd-0f3e-41e4-ae9a-5284d0376d05"),
    orderDto = getOrderWithUserDto(),
    productDto = getProduct1(),
    quantity = 12,
    unitCost = 10.34.toBigDecimal(),
    createdAt = LocalDateTime.parse("2024-11-11T20:35:00"),
    createdBy = "SYSTEM"
)
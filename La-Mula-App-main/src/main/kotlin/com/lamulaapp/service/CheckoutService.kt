package com.lamulaapp.service

import com.lamulaapp.controller.dto.CheckoutRequestDto
import com.lamulaapp.controller.dto.CheckoutResponseDto
import com.lamulaapp.controller.dto.OrderDto
import com.lamulaapp.controller.mapper.toDto
import com.lamulaapp.controller.mapper.toEntity
import com.lamulaapp.controller.utils.calculateTotalCost
import com.lamulaapp.domain.Order
import com.lamulaapp.domain.OrderDetail
import com.lamulaapp.domain.OrderStatus
import com.lamulaapp.exception.EntityNotFoundException
import com.lamulaapp.repository.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CheckoutService(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val orderRepository: OrderRepository,
    private val orderDetailRepository: OrderDetailRepository,
    private val orderStatusRepository: OrderStatusRepository,
    private val productRepository: ProductRepository
) {

    companion object {
        const val ROLE_CLIENT = "CLIENT"
        const val ROLE_SELLER = "SELLER"
        const val ORDER_STATUS_IN_PROGRESS = "IN_PROGRESS"
    }

    fun checkout(checkoutRequestDto: CheckoutRequestDto): CheckoutResponseDto {
        val checkoutResponseDto = CheckoutResponseDto()

        // Get the order status entity
        val orderStatusEntity = orderStatusRepository.findByTypeStatus(ORDER_STATUS_IN_PROGRESS).get()

        // Verify the entity type
        if (checkoutRequestDto.rolName == ROLE_CLIENT) {
            checkoutResponseDto.orderDto = checkoutUser(checkoutRequestDto, orderStatusEntity)
        }

        if (checkoutRequestDto.rolName == ROLE_SELLER) {
            checkoutResponseDto.orderDto = checkoutCompany(checkoutRequestDto, orderStatusEntity)
        }

        return checkoutResponseDto
    }

    fun checkoutUser(checkoutRequestDto: CheckoutRequestDto, orderStatus: OrderStatus): OrderDto {
        // Find entity by ID
        val userEntity = userRepository.findById(checkoutRequestDto.idEntity!!)

        if (!userEntity.isPresent) {
            throw EntityNotFoundException("The user with the ID: ${checkoutRequestDto.idEntity} was not found!")
        }

        // Create the order
        var orderEntity = Order(
            user = userEntity.get(),
            orderStatus = orderStatus,
            totalCost = calculateTotalCost(checkoutRequestDto.productQuantityList!!),
            createdAt = LocalDateTime.now(),
            createdBy = checkoutRequestDto.username!!
        )
        orderEntity = orderRepository.saveAndFlush(orderEntity)

        // Create the order details
        for (productQuantity in checkoutRequestDto.productQuantityList) {
            val orderDetail = OrderDetail(
                order = orderEntity,
                product = productQuantity.productDto.toEntity(),
                quantity = productQuantity.quantity, // This is the number of products to buy
                unitCost = productQuantity.productDto.unitCost!!, // This is the current product unit cost (this price usually change over the time)
                createdAt = LocalDateTime.now(),
                createdBy = checkoutRequestDto.username
            )
            orderDetailRepository.save(orderDetail)

            // Update the product stock
            val productEntity = productQuantity.productDto.toEntity().copy(
                stock = productQuantity.productDto.stock!! - productQuantity.quantity,
                updatedAt = LocalDateTime.now(),
                updatedBy = checkoutRequestDto.username
            )
            productRepository.save(productEntity)
        }

        // Return the order info
        return orderEntity.toDto()
    }

    fun checkoutCompany(checkoutRequestDto: CheckoutRequestDto, orderStatus: OrderStatus): OrderDto {
        // Find entity by ID
        val companyEntity = companyRepository.findById(checkoutRequestDto.idEntity!!)

        if (!companyEntity.isPresent) {
            throw EntityNotFoundException("The company with the ID: ${checkoutRequestDto.idEntity} was not found!")
        }

        // Create the order
        val orderEntity = Order(
            company = companyEntity.get(),
            orderStatus = orderStatus,
            totalCost = calculateTotalCost(checkoutRequestDto.productQuantityList!!),
            createdAt = LocalDateTime.now(),
            createdBy = checkoutRequestDto.username!!
        )
        orderRepository.saveAndFlush(orderEntity)

        // Create the order details
        for (productQuantity in checkoutRequestDto.productQuantityList) {
            val orderDetail = OrderDetail(
                order = orderEntity,
                product = productQuantity.productDto.toEntity(),
                quantity = productQuantity.quantity,
                unitCost = productQuantity.productDto.unitCost!!,
                createdAt = LocalDateTime.now(),
                createdBy = checkoutRequestDto.username
            )
            orderDetailRepository.save(orderDetail)

            // Update the product stock
            val productEntity = productQuantity.productDto.toEntity().copy(
                stock = productQuantity.productDto.stock!! - productQuantity.quantity,
                updatedAt = LocalDateTime.now(),
                updatedBy = checkoutRequestDto.username
            )
            productRepository.save(productEntity)
        }

        // Return the order info
        return orderEntity.toDto()
    }
}
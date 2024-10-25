package com.lamulaapp.repository

import com.lamulaapp.domain.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderStatusRepository: JpaRepository<OrderStatus, UUID> {
}
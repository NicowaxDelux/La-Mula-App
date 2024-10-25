package com.lamulaapp.repository

import com.lamulaapp.domain.Orders
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrdersRepository: JpaRepository<Orders, UUID> {
}
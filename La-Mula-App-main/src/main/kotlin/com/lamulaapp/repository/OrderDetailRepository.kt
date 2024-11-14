package com.lamulaapp.repository

import com.lamulaapp.domain.OrderDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderDetailRepository : JpaRepository<OrderDetail, UUID> {
    fun findAllByOrder_IdOrder(idOrder: UUID): Optional<List<OrderDetail>>
}
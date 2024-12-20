package com.lamulaapp.repository

import com.lamulaapp.domain.Order
import org.springframework.data.domain.Limit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository: JpaRepository<Order, UUID> {
    fun findAllByUser_IdUser(idUser: UUID, limit: Limit): Optional<List<Order>>
}
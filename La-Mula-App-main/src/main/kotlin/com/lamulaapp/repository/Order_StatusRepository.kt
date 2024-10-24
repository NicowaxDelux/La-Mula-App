package com.lamulaapp.repository

import com.lamulaapp.domain.Order_Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface Order_StatusRepository: JpaRepository<Order_Status, UUID> {
}
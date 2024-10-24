package com.lamulaapp.repository

import com.lamulaapp.domain.CoffeeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CoffeeTypeRepository : JpaRepository<CoffeeType, UUID> {
}

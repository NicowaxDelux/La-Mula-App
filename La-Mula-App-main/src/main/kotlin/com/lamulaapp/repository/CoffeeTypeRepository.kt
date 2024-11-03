package com.lamulaapp.repository

import com.lamulaapp.domain.CoffeeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CoffeeTypeRepository : JpaRepository<CoffeeType, UUID> {

    fun findByName(name: String): Optional<CoffeeType>
}

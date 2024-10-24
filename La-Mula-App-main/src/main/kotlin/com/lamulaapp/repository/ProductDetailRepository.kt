package com.lamulaapp.repository

import com.lamulaapp.domain.ProductDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductDetailRepository : JpaRepository<ProductDetail, UUID> {
}
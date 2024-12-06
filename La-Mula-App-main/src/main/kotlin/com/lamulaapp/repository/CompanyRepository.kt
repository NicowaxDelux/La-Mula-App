package com.lamulaapp.repository

import com.lamulaapp.domain.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CompanyRepository : JpaRepository<Company, UUID>
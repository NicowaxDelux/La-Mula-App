package com.lamulaapp.repository

import com.lamulaapp.domain.Login
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LoginRepository: JpaRepository<Login, UUID> {
}
package com.lamulaapp.repository

import com.lamulaapp.domain.Roles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RolesRepository: JpaRepository<Roles, UUID> {
}
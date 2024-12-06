package com.lamulaapp.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "coffee_types")
data class CoffeeType(

    @Id
    @Column(name = "id_coffee_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idCoffeeType: UUID? = null,

    val name: String,

    val description: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "created_by")
    val createdBy: String,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "updated_by")
    val updatedBy: String? = null
)

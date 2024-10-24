package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "coffee_types")
data class CoffeeType(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_coffee_type", nullable = false)
    val idCoffeeType: UUID? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "description", nullable = false)
    val description: String
)

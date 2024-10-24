package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product", nullable = false)
    val idProduct: UUID? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_coffe_type", nullable = false)
    val coffeeType: CoffeeType,

    @Column(name = "description", nullable = true)
    val description: String? = null,

    @Column(name = "unit_cost", nullable = false)
    val unitCost: Double,

    @Column(name = "quantity", nullable = false)
    val quantity: Int
)

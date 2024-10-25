package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    val idProduct: UUID? = null,

    val name: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_coffe_type", nullable = false)
    val coffeeType: CoffeeType,

    val description: String? = null,

    @Column(name = "unit_cost", nullable = false)
    val unitCost: Double,

    val quantity: Int,

    @Column( name = "create_at")
    val createDate: LocalDateTime,

    @Column( name = "create_by")
    val createBy: String,

    @Column( name = "update_at")
    val updateAt: LocalDateTime,

    @Column( name = "update_by")
    val updateBy: String
)

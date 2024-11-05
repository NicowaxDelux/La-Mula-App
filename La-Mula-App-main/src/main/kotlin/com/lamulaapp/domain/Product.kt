package com.lamulaapp.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    val idProduct: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coffee_type")
    val coffeeType: CoffeeType? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_attachment", nullable = false)
    val attachment: Attachment,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_company", nullable = false)
    val company: Company,

    val name: String,

    val description: String? = null,

    @Column(name = "unit_cost", nullable = false)
    val unitCost: BigDecimal,

    val quantity: Int,

    @Column(name = "package_size", nullable = false)
    val packageSize: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "created_by")
    val createdBy: String,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "updated_by")
    val updatedBy: String? = null
)

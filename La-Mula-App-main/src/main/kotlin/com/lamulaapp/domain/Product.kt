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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_coffee_type", nullable = false)
    val coffeeType: CoffeeType,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_attachment", nullable = false)
    val attachment: Attachment,

    val name: String,

    val description: String? = null,

    @Column(name = "unit_cost", nullable = false)
    val unitCost: BigDecimal,

    val quantity: Int,

    @Column(name = "package_size", nullable = false)
    val packageSize: String,

    @Column( name = "create_at")
    val createDate: LocalDateTime,

    @Column( name = "create_by")
    val createBy: String,

    @Column( name = "update_at")
    val updateAt: LocalDateTime,

    @Column( name = "update_by")
    val updateBy: String
)

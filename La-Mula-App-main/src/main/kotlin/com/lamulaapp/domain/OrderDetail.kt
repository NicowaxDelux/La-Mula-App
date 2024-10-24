package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "order_details")
data class OrderDetail(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_order_detail", nullable = false)
    val idOrderDetail: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_order", nullable = false)
    val order: Order,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_product", nullable = false)
    val product: Product,

    @Column(name = "quantity", nullable = false)
    val quantity: Int,

    @Column(name = "unit_cost", nullable = false)
    val unitCost: Double
)

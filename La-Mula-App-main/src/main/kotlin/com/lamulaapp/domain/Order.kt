package com.lamulaapp.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @Column(name = "id_order")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    val idOrder: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_oder_status", nullable = false)
    val orderStatus: OrderStatus,

    @Column(name = "order_code", unique = true, insertable = false, updatable = false)
    val orderCode: Long? = null,

    @Column(name = "total_cost")
    val totalCost: BigDecimal,

    @Column( name = "created_at")
    val createdAt: LocalDateTime,

    @Column( name = "created_by")
    val createdBy: String,

    @Column( name = "updated_at")
    val updatedAt: LocalDateTime,

    @Column( name = "updated_by")
    val updatedBy: String
)

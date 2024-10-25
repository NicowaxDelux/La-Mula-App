package com.lamulaapp.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "order_statuses")
data class OrderStatus(
    @Id
    @Column(name = "id_order_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idOrderStatus: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "description")
    val description: String,

    @Column( name = "created_at")
    val createdAt: LocalDateTime,

    @Column( name = "created_by")
    val createdBy: String,

    @Column( name = "updated_at")
    val updatedAt: LocalDateTime,

    @Column( name = "updated_by")
    val updatedBy: String,
    )

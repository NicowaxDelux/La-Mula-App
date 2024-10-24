package com.lamulaapp.domain

import jakarta.persistence.*
import java.io.Serial
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "orders")
data class Orders(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    val idUser: Users? = null,

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oder_status", nullable = false)
    val idOrderStatus: OrderStatus? = null,

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderCode: Serial,

    @Column(name = "total_cost")
    val totalCost: Float,

    @Column( name = "create_at")
    val createDate: LocalDateTime,

    @Column( name = "create_by")
    val createBy: String,

    @Column( name = "update_at")
    val updateAt: LocalDateTime,

    @Column( name = "update_by")
    val updateBy: String,
    )

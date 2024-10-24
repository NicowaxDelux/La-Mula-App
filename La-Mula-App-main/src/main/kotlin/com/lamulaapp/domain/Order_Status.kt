package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "order_statuses")
data class Order_Status(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "description")
    val description: String,

    @Column( name = "create_at")
    @Temporal( TemporalType.DATE)
    var createdate: Date,

    @Column( name = "create_by")
    var createby: String,

    @Column( name = "update_at")
    @Temporal(TemporalType.DATE)
    var updateat: Date,

    @Column( name = "update_by")
    var updateby: String,
    )

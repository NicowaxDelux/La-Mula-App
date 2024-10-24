package com.lamulaapp.domain

import jakarta.persistence.*
import java.io.Serial
import java.util.*

data class Orders(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    val iduser: Users? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oder_status", referencedColumnName = "id_order_status")
    val idorderstatus: Order_Status? = null,

    val ordercode: Serial,

    @Column(name = "total_cost")
    val totalcost: Float,

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

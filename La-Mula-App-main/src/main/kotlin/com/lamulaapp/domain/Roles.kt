package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "roles")
data class Roles (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val idrole: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    val createat: Date,

    @Column(name = "create_by")
    val createby: String,

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    val updateat: Date,

    @Column(name = "update_by")
    val updateby: String,
)
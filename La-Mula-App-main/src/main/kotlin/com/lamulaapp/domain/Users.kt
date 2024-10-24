package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    val idrole: Roles? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "address")
    val address: String,

    @Column(name = "phone")
    val phone: Int,

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

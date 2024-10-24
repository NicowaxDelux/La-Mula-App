package com.lamulaapp.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    val idRole: Roles? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "address")
    val address: String,

    @Column(name = "phone")
    val phone: Int,

    @Column( name = "create_at")
    val createDate: LocalDateTime,

    @Column( name = "create_by")
    val createBy: String,

    @Column( name = "update_at")
    val updateAt: LocalDateTime,

    @Column( name = "update_by")
    val updateBy: String,

    )

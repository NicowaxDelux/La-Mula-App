package com.lamulaapp.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table (name = "logins")
data class Login(
    @Id
    @Column(name = "id_login")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val idLogin: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    val user: User,

    val username: String,

    val password: String,

    @Column( name = "created_at")
    val createdAt: LocalDateTime,

    @Column( name = "created_by")
    val createdBy: String,

    @Column( name = "updated_at")
    val updatedAt: LocalDateTime,

    @Column( name = "updated_by")
    val updatedBy: String
)
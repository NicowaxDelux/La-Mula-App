package com.lamulaapp.domain

import com.lamulaapp.domain.enums.RolesEnum
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

    @Enumerated(EnumType.STRING)
    val role: RolesEnum,

    @Column(unique = true)
    val username: String,

    val password: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "created_by")
    val createdBy: String,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "updated_by")
    val updatedBy: String? = null
)
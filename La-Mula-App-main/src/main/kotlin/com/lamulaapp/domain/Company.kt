package com.lamulaapp.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "companies")
data class Company (
    @Id
    @Column(name = "id_company")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idCompany: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_login", nullable = false)
    val login: Login,

    val name: String,

    @Column(unique = true)
    val email: String,

    val address: String? = null,

    val phone: String? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "created_by")
    val createdBy: String,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "updated_by")
    val updatedBy: String? = null,

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val products: MutableSet<Product> = mutableSetOf()
)
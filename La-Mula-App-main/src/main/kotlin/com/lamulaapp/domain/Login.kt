package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table (name = "login")
data class Login(
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    val iduser: Users? = null,

    @Column( name = "username")
    val username: String,

    @Column( name = "password")
    val password: String,

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
package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "coffee_types")
data class CoffeeType(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coffee_type", nullable = false)
    val idCoffeeType: UUID? = null,

    val name: String,

    val description: String,

    @Column( name = "create_at")
    val createDate: LocalDateTime,

    @Column( name = "create_by")
    val createBy: String,

    @Column( name = "update_at")
    val updateAt: LocalDateTime,

    @Column( name = "update_by")
    val updateBy: String
)

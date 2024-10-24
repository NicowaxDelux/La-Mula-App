package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "product_details")
data class ProductDetail(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product_detail", nullable = false)
    val idProductDetail: UUID? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "package_size", nullable = false)
    val packageSize: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_attachment", nullable = false)
    val attachment: Attachment
)

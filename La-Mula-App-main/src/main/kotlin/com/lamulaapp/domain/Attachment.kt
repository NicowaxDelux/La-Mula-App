package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "attachments")
data class Attachment(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_attachment", nullable = false)
    val idAttachment: UUID? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Lob
    @Column(name = "content", nullable = false)
    val content: ByteArray,

    @Column(name = "content_type", nullable = false)
    val contentType: String
)

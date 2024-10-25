package com.lamulaapp.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "attachments")
data class Attachment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_attachment", nullable = false)
    val idAttachment: UUID? = null,

    val name: String,

    @Lob
    val content: ByteArray,

    @Column(name = "content_type", nullable = false)
    val contentType: String,

    @Column( name = "create_at")
    val createDate: LocalDateTime,

    @Column( name = "create_by")
    val createBy: String,

    @Column( name = "update_at")
    val updateAt: LocalDateTime,

    @Column( name = "update_by")
    val updateBy: String
)

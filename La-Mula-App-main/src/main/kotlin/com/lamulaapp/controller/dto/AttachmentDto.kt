package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.*

data class AttachmentDto(
    val idAttachment: UUID? = null,
    val name: String,
    val content: ByteArray,
    val contentType: String,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val updatedAt: LocalDateTime,
    val updatedBy: String
)
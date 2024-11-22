package com.lamulaapp.controller.dto

import java.time.LocalDateTime
import java.util.*

data class AttachmentDto(
    val idAttachment: UUID? = null,
    val name: String? = null,
    val content: String? = null,
    val url: String? = null,
    val contentType: String? = null,
    val createdAt: LocalDateTime? = null,
    val createdBy: String? = null,
    val updatedAt: LocalDateTime? = null,
    val updatedBy: String? = null
)
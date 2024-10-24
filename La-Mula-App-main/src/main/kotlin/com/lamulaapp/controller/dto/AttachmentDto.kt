package com.lamulaapp.controller.dto

import java.util.*

data class AttachmentDto(
    val idAttachment: UUID? = null,
    val name: String,
    val content: String, // Asumo que se enviará como String en base64
    val contentType: String
)
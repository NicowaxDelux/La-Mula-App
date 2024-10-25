package com.lamulaapp.controller.mapper

import com.lamulaapp.controller.dto.AttachmentDto
import com.lamulaapp.domain.Attachment

fun Attachment.toDto() = AttachmentDto(
    idAttachment = this.idAttachment,
    name = this.name,
    content = this.content,
    contentType = this.contentType,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)

fun AttachmentDto.toEntity() = Attachment(
    idAttachment = this.idAttachment,
    name = this.name,
    content = this.content,
    contentType = this.contentType,
    createdAt = this.createdAt,
    createdBy = this.createdBy,
    updatedAt = this.updatedAt,
    updatedBy = this.updatedBy
)